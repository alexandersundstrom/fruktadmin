package com.evry.server.servlet;

import com.evry.fruktkorgservice.service.FruktkorgService;
import com.evry.server.util.Beans;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class UploadXMLServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        ServletFileUpload upload = new ServletFileUpload();
        FruktkorgService fruktkorgService = Beans.getBean("fruktkorgService");
        try {
            FileItemIterator itr = upload.getItemIterator(request);
            while (itr.hasNext()) {
                FileItemStream item = itr.next();

                if (!item.getContentType().equals("text/xml") && !item.getContentType().equals("application/xml")) {
                    throw new RuntimeException("File must be of typ XML, but was: " + item.getContentType());
                }

                InputStream stream = item.openStream();
                fruktkorgService.updateFruktkorgar(stream);
                stream.close();
            }
        } catch (FileUploadException e) {
            // do something
        } catch (IOException e) {
            // do something else
        }

        response.setStatus(200);
        try {
            response.setHeader("Content-Type", "application/json");
            response.getWriter().print("{\"success\": true, \"message\": \"File uploaded\"}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
