package com.evry.server.servlet.util;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {

    public static void sendFile(HttpServletResponse resp, byte[] bytes, String name, String content) throws IOException {
        ServletOutputStream servletOutputStream = null;

        servletOutputStream = resp.getOutputStream();
        resp.setContentType(content);
        resp.setHeader("Content-Type", content);
        resp.setHeader("Content-Disposition", "attachment; filename=" + name);
        resp.setContentLength(bytes.length);
        servletOutputStream.write(bytes);
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
