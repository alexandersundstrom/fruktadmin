package com.evry.server.servlet;

import com.evry.fruktkorgservice.domain.model.ImmutableFrukt;
import com.evry.fruktkorgservice.domain.model.ImmutableFruktkorg;
import com.evry.fruktkorgservice.domain.service.ReportService;
import com.evry.fruktkorgservice.exception.ReportMissingException;
import com.evry.server.util.Beans;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class DownloadReportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReportService reportService = Beans.getBean("reportService");

        Cookie[] cookies = req.getCookies();
        long reportId = -1;
        FileType fileType = FileType.XML;
        String name = "";

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("REPORT_ID") && isLong(cookie.getValue())) {
                reportId = Long.parseLong(cookie.getValue());
            } else if (cookie.getName().equals("REPORT_FILE_TYPE")) {
                fileType = FileType.valueOf(cookie.getValue());
            }
            name = req.getParameter("name");
        }

        if (reportId == -1) {
            return;
        }

        byte[] bytes;
        try {
            bytes = IOUtils.toByteArray(reportService.getAndMarkReport(reportId));
        } catch (ReportMissingException e) {
            // do something
            return;
        }

        try {
            switch (fileType) {
                case XML:
                    name += ".xml";
                    break;
                case PDF:
                    bytes = getPdfFile(reportId);
                    name += ".pdf";
                    break;
            }
        } catch (DocumentException e) {
            // do something
        } catch (ReportMissingException e) {
            // do something else
        }

        if (bytes == null) {
            return;
        }

        sendReport(resp, bytes, name, fileType.getContentType());
    }

    private byte[] getPdfFile(long reportId) throws IOException, DocumentException, ReportMissingException {
        File pdfReport = File.createTempFile("pdfReport-", ".tmp");
        pdfReport.deleteOnExit();

        ReportService reportService = Beans.getBean("reportService");
        List<ImmutableFruktkorg> fruktkorgList = reportService.getFruktkorgarFromReport(reportId);

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(pdfReport));

        document.open();
        document.addTitle("Fruktkorg Report");
        for (ImmutableFruktkorg fruktkorg : fruktkorgList) {
            document.add(new Paragraph(fruktkorg.getName() + " - " + fruktkorg.getLastChanged()));
            com.itextpdf.text.List list = new com.itextpdf.text.List(com.itextpdf.text.List.UNORDERED);

            for (ImmutableFrukt frukt : fruktkorg.getFruktList()) {
                ListItem item = new ListItem(frukt.getType() + ": " + frukt.getAmount());
                item.setAlignment(Element.ALIGN_JUSTIFIED);
                list.add(item);
            }

            document.add(list);
        }

        document.close();

        return getBytesFromFile(pdfReport);
    }

    private byte[] getBytesFromFile(File reportFile) {
        byte[] bytes = null;

        try {
            FileInputStream fileInputStream = new FileInputStream(reportFile);
            bytes = new byte[(int) reportFile.length()];
            fileInputStream.read(bytes);
        } catch (IOException e) {
            // do nothing
        }

        return bytes;
    }

    private void sendReport(HttpServletResponse resp, byte[] bytes, String name, String content) throws IOException {
        ServletOutputStream servletOutputStream = null;

        servletOutputStream = resp.getOutputStream();
        resp.setContentType(content);
        resp.setHeader("Content-Type", content);
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

    public enum FileType {
        XML("application/xml"),
        PDF("application/pdf");

        private String contentType;

        FileType(String contentType) {
            this.contentType = contentType;
        }

        public String getContentType() {
            return contentType;
        }
    }
}
