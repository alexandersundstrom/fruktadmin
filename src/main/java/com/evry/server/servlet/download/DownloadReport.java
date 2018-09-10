package com.evry.server.servlet.download;


import com.evry.fruktkorgservice.exception.ReportMissingException;
import com.evry.server.servlet.download.model.FileInformationHolder;
import com.evry.server.servlet.util.FileUtil;
import com.evry.server.servlet.util.FileUtil.FileType;
import com.evry.server.servlet.util.ResponseUtil;
import com.itextpdf.text.DocumentException;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DownloadReport extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        long reportId = -1;
        //defaultType is XML
        FileType fileType = FileType.XML;

        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("REPORT_ID") && NumberUtils.isNumber(cookie.getValue())) {
                reportId = Long.parseLong(cookie.getValue());
            } else if (cookie.getName().equals("REPORT_FILE_TYPE")) {
                fileType = FileType.valueOf(cookie.getValue());
            }
        }

        boolean reportIdIsNotSet = reportId == -1;
        if (reportIdIsNotSet) {
            ResponseUtil.send400("Inget id satt för rapport, kontakta administratören", resp);
        }

        String reportName = req.getParameter("name");
        String filename = FileUtil.createReportFilename(reportName, fileType);

        byte[] report = null;
        try {
            report = FileUtil.getBytesfromReport(reportId, fileType);
        } catch (ReportMissingException e) {
            ResponseUtil.send400("Ingen rapport med id " + reportId + "kunde hittas", resp);
        } catch (DocumentException e) {
            ResponseUtil.send400("Följande fel inträffade: " + e.getMessage(), resp);
        }

        FileInformationHolder fileHolder = new FileInformationHolder(report, filename, fileType.getContentType());
        ResponseUtil.sendFile(fileHolder, resp);
    }
}
