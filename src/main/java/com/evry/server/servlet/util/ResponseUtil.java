package com.evry.server.servlet.util;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {

    public static HttpServletResponse sendFile(HttpServletResponse resp, byte[] bytes, String name, String content) throws IOException {
        ServletOutputStream servletOutputStream = null;

        servletOutputStream = resp.getOutputStream();
        resp.setContentType(content);
        resp.setHeader("Content-Type", content);
        resp.setHeader("Content-Disposition", "attachment; filename=" + name);
        resp.setContentLength(bytes.length);
        servletOutputStream.write(bytes);
        servletOutputStream.close();

        return resp;
    }


}
