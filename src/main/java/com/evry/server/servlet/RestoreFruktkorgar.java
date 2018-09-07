package com.evry.server.servlet;

import com.evry.client.util.Log;
import com.evry.fruktkorgservice.FruktkorgService;
import com.evry.fruktkorgservice.exception.FruktMissingException;
import com.evry.fruktkorgservice.exception.FruktkorgMissingException;
import com.evry.server.util.Beans;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;

public class RestoreFruktkorgar extends HttpServlet {
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

                try (InputStream stream = item.openStream()) {

                    fruktkorgService.restoreFruktkorgar(stream);
                    response.setStatus(200);
                    response.setHeader("Content-Type", "text/html");
                    response.getWriter().print("{\"success\": true, \"message\": \"Fruktkorgar återställda.\"}");

                } catch (FruktMissingException e) {
                    response.setStatus(400);
                    response.setHeader("Content-Type", "text/html");
                    response.getWriter().print("{\"success\": false, \"message\": \"" + e.getMessage() + "\"}");
                } catch (FruktkorgMissingException e) {
                    response.setStatus(400);
                    response.setHeader("Content-Type", "text/html");
                    response.getWriter().print("{\"success\": false, \"message\": \"" + e.getMessage() + "\"}");
                } catch (IOException e) {
                    Log.warn("Caught an IOException: " + e.getMessage());
                } catch (JAXBException e) {
                    response.setStatus(400);
                    response.setHeader("Content-Type", "text/html");
                    response.getWriter().print("{\"success\": false, \"message\": \"" + "XML filen kunde inte konverteras till Fruktkorgar. Säkerställ att den följer schema definitionen." + "\"}");
                }
            }
        } catch (FileUploadException e) {
            response.setStatus(400);
            response.setHeader("Content-Type", "text/html");
            try {
                response.getWriter().print("{\"success\": false, \"message\": \"" + e.getMessage() + "\"}");
            } catch (IOException e1) {

            }
        } catch (IOException e) {
            Log.warn("Caught an IOException: " + e.getMessage());
        }
    }
}
