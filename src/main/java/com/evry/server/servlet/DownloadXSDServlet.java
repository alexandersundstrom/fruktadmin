package com.evry.server.servlet;

import com.evry.fruktkorgservice.domain.service.ReportService;
import com.evry.server.util.Beans;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DownloadXSDServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReportService reportService = Beans.getBean("reportService");

        String schema = req.getParameter("schema");

        if (schema == null) {
            return;
        }

        if (schema.equals("update")) {
            byte[] bytes = IOUtils.toByteArray(reportService.getUpdateXSD());
            if (bytes != null) {
                sendSchema(resp, bytes, "update.xsd", "application/xsd");
            }
        } else if (schema.equals("restore")) {
            byte[] bytes = IOUtils.toByteArray(reportService.getRestoreXSD());
            if (bytes != null) {
                sendSchema(resp, bytes, "restore.xsd", "application/xsd");
            }
        }
    }

    private void sendSchema(HttpServletResponse resp, byte[] bytes, String name, String content) throws IOException {
        ServletOutputStream servletOutputStream = null;

        servletOutputStream = resp.getOutputStream();
        resp.setContentType(content);
        resp.setHeader("Content-Type", content);
        resp.setHeader("Content-Disposition", "attachment; filename=" + name);
        resp.setContentLength(bytes.length);
        servletOutputStream.write(bytes);
        servletOutputStream.close();
    }
}
