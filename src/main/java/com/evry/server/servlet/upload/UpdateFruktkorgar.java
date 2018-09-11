package com.evry.server.servlet.upload;

import com.evry.client.util.Log;
import com.evry.fruktkorgservice.FruktkorgService;
import com.evry.fruktkorgservice.exception.FruktkorgMissingException;
import com.evry.server.servlet.common.util.ResponseUtil;
import com.evry.server.Beans;
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

public class UpdateFruktkorgar extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        FruktkorgService fruktkorgService = Beans.getBean("fruktkorgService");
        ServletFileUpload upload = new ServletFileUpload();
        try {
            FileItemIterator itr = upload.getItemIterator(request);
            while (itr.hasNext()) {
                FileItemStream item = itr.next();

                if (!item.getContentType().equals("text/xml") && !item.getContentType().equals("application/xml")) {
                    throw new RuntimeException("File must be of typ XML, but was: " + item.getContentType());
                }

                try (InputStream stream = item.openStream()) {
                    fruktkorgService.updateFruktkorgar(stream);
                    ResponseUtil.send200("Fruktkorgar uppdaterades.", response);

                } catch (FruktkorgMissingException e) {
                    ResponseUtil.send400(e.getMessage(), response);
                } catch (IOException e) {
                    ResponseUtil.send400(e.getMessage(), response);
                } catch (JAXBException e) {
                    ResponseUtil.send400("XML filen kunde inte konverteras till Fruktkorgar. Säkerställ att den följer schema definitionen.", response);
                }
            }
        } catch (FileUploadException e) {
            try {
                ResponseUtil.send400(e.getMessage(), response);
            } catch (IOException e1) {
                Log.warn("Caught an IOException " + e.getMessage());
            }
        } catch (IOException e) {
            Log.warn("Caught an IOException: " + e.getMessage());
        }
    }
}
