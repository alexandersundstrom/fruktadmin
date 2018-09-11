package com.evry.server.servlet.download;


import com.evry.fruktkorgservice.exception.ReportMissingException;
import com.evry.server.servlet.common.ContentType;
import com.evry.server.servlet.common.util.FileUtil;
import com.evry.server.servlet.common.util.ResponseUtil;
import com.evry.server.servlet.download.model.FileInformationHolder;
import com.itextpdf.text.DocumentException;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;
import java.io.IOException;

public class DownloadReport extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            loadAndSendReport(req, resp);
        } catch (ValidationException e) {
            ResponseUtil.send400("Inget id satt för rapport, kontakta administratören", resp);
        } catch (ReportMissingException e) {
            ResponseUtil.send400("Ingen rapport med id funnet: " + e.getMessage(), resp);
        } catch (DocumentException e) {
            ResponseUtil.send400("Följande fel inträffade: " + e.getMessage(), resp);
        }
    }

    private void loadAndSendReport(HttpServletRequest req, HttpServletResponse resp) throws ValidationException, IOException, ReportMissingException, DocumentException {
        String idParameter = req.getParameter("id");
        validateReportId(idParameter);
        Long reportId = Long.parseLong(idParameter);

        String contentTypeParamater = req.getParameter("type");
        ContentType contentType = contentTypeParamater != null ? ContentType.valueOf(contentTypeParamater) : ContentType.XML;

        String reportName = req.getParameter("name");
        String reportFilename = FileUtil.createReportFilename(reportName, contentType);

        byte[] reportAsBytes = FileUtil.getBytesfromReport(reportId, contentType);
        FileInformationHolder fileHolder = new FileInformationHolder(reportAsBytes, reportFilename, contentType.getContentType());
        ResponseUtil.sendFile(fileHolder, resp);
    }

    private void validateReportId(String idParameter) throws ValidationException {
        if (!NumberUtils.isNumber(idParameter)) {
            throw new ValidationException("Report id inte satt korrekt som parameter.");
        }
    }
}
