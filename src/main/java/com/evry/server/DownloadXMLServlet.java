package com.evry.server;

import com.evry.fruktkorgservice.exception.ReportMissingException;
import com.evry.fruktkorgservice.model.ImmutableReport;
import com.evry.fruktkorgservice.service.ReportService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DownloadXMLServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReportService reportService = (ReportService) Beans.getBean("reportService");

        Cookie[] cookies = req.getCookies();
        long reportId = -1;
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals("REPORT_ID")) {
                if(isLong(cookie.getValue())) {
                    reportId = Long.parseLong(cookie.getValue());
                }

                break;
            }
        }

        if(reportId == -1) {
            return;
        }

        ImmutableReport immutableReport;
        try {
            immutableReport = reportService.getAndMarkReport(reportId);
        } catch (ReportMissingException e) {
            // do something
            return;
        }

        byte[] bytes = getFile(immutableReport.getLocation());
        String[] split = immutableReport.getLocation().split("/");
        sendXML(resp, bytes, split[split.length - 1]);
    }

    private byte[] getFile(String path) {
        byte[] bytes = null;

        try {
            File file = new File(path);
            FileInputStream fileInputStream = new FileInputStream(file);
            bytes = new byte[(int) file.length()];
            fileInputStream.read(bytes);
        } catch(IOException e) {
            // do nothing
        }

        return bytes;
    }

    private void sendXML(HttpServletResponse resp, byte[] bytes, String name) throws IOException {
        ServletOutputStream servletOutputStream = null;

        servletOutputStream = resp.getOutputStream();
        resp.setContentType("application/xml");
        resp.setHeader("Content-Type", "application/xml");
        resp.setHeader("Content-Disposition", "attachment; filename=" + name);
        resp.setContentLength(bytes.length);
        servletOutputStream.write(bytes);
        servletOutputStream.close();
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
