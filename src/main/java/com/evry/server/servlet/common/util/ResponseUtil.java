package com.evry.server.servlet.common.util;

import com.evry.server.servlet.download.model.FileInformationHolder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {

    public static void sendFile(FileInformationHolder fileHolder, HttpServletResponse resp) throws IOException {
        ServletOutputStream servletOutputStream = resp.getOutputStream();
        resp.setContentType(fileHolder.getContentType());
        resp.setHeader("Content-Type", fileHolder.getContentType());
        resp.setHeader("Content-Disposition", "attachment; filename=" + fileHolder.getFilename());
        resp.setContentLength(fileHolder.getFileAsBytes().length);
        servletOutputStream.write(fileHolder.getFileAsBytes());
        servletOutputStream.close();
    }

    public static void send400(String message, HttpServletResponse resp) throws IOException {
        resp.setStatus(400);
        resp.setHeader("Content-Type", "text/html");
        resp.getWriter().print("{\"success\": false, \"message\": \"" + message + "\"}");

    }

    public static void send200(String message, HttpServletResponse resp) throws IOException {
        resp.setStatus(200);
        resp.setHeader("Content-Type", "text/html");
        resp.getWriter().print("{\"success\": true, \"message\": \"" + message + "\"}");

    }


}
