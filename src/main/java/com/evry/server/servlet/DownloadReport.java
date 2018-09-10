package com.evry.server.servlet;


import com.evry.fruktkorgservice.exception.ReportMissingException;
import com.evry.server.servlet.util.FileUtil;
import com.evry.server.servlet.util.FileUtil.FileType;
import com.evry.server.servlet.util.ResponseUtil;
import com.itextpdf.text.DocumentException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DownloadReport extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        long reportId = -1;
        FileType fileType = FileType.XML;

        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("REPORT_ID") && isLong(cookie.getValue())) {
                reportId = Long.parseLong(cookie.getValue());
            } else if (cookie.getName().equals("REPORT_FILE_TYPE")) {
                fileType = FileType.valueOf(cookie.getValue());
            }
        }

        String reportName = req.getParameter("name");
        String filename = FileUtil.createReportFilename(reportName, fileType);

        boolean reportIdIsNotSet = reportId == -1;
        if (reportIdIsNotSet) {
          //return error status
        }

        byte[] report = null;
        try {
            report = FileUtil.getBytesfromReport(reportId, fileType);
        } catch (ReportMissingException e) {
            //return error status
        } catch (DocumentException e) {
            //return error status
        }

        ResponseUtil.sendFile(resp, report, filename, fileType.getContentType());
    }

    private boolean isLong(String string) {
        try {
            Long.parseLong(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
