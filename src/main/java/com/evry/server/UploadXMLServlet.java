package com.evry.server;

import com.evry.fruktkorgservice.model.ImmutableFruktkorg;
import com.evry.fruktkorgservice.service.ReportService;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

public class UploadXMLServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        ServletFileUpload upload = new ServletFileUpload();
        ReportService reportService = (ReportService) Beans.getBean("reportService");
        try {
            FileItemIterator iter = upload.getItemIterator(request);

            while (iter.hasNext()) {
                FileItemStream item = iter.next();

                if (!item.getContentType().equals("text/xml")) {
                    throw new RuntimeException("File must be of typ XML");
                }
                InputStream stream = item.openStream();
                byte[] buffer = IOUtils.toByteArray(stream);
                List<ImmutableFruktkorg> immutableFruktkorgs = reportService.readFromByteArrayAndUpdateFruktkorgar(buffer);
                stream.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
