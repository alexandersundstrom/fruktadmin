package com.evry.server;

import com.evry.fruktkorgservice.service.FruktkorgService;
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
        FruktkorgService fruktkorgService = (FruktkorgService) Beans.getBean("fruktkorgService");
        try {
            FileItemIterator itr = upload.getItemIterator(request);
            while (itr.hasNext()) {
                FileItemStream item = itr.next();

                if (!item.getContentType().equals("text/xml") || !item.getContentType().equals("application/xml")) {
                    throw new RuntimeException("File must be of typ XML");
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

    }
}
