package com.evry.server.servlet.common.util;

import com.evry.fruktkorgservice.ReportService;
import com.evry.fruktkorgservice.exception.ReportMissingException;
import com.evry.fruktkorgservice.model.ImmutableFrukt;
import com.evry.fruktkorgservice.model.ImmutableFruktkorg;
import com.evry.server.Beans;
import com.evry.server.servlet.common.ContentType;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.List;

public class FileUtil {

    private static ReportService reportService = Beans.getBean("reportService");

    public static byte[] getPdfAsBytes(long reportId) throws IOException, DocumentException, ReportMissingException {
        File pdfReport = File.createTempFile("pdfReport-", ".tmp");
        pdfReport.deleteOnExit();

        List<ImmutableFruktkorg> fruktkorgList = reportService.getFruktkorgarFromReport(reportId);

        //null check on fruktkorgar

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(pdfReport));

        document.open();
        document.addTitle("Fruktkorg FileInformationHolder");
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

        return getFileAsBytes(pdfReport);
    }

    public static byte[] getFileAsBytes(File file) {
        byte[] bytes = null;

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            bytes = new byte[(int) file.length()];
            fileInputStream.read(bytes);
        } catch (IOException e) {
            // do nothing
        }

        return bytes;
    }

    public static byte[] getBytesfromReport(long reportId, ContentType wantedContentType) throws IOException, ReportMissingException, DocumentException {
        byte[] reportAsBytes = null;

        switch (wantedContentType) {
            case XML:
                reportAsBytes = IOUtils.toByteArray(reportService.getAndMarkReport(reportId));
                break;
            case PDF:
                reportAsBytes = FileUtil.getPdfAsBytes(reportId);
                break;
        }
        return reportAsBytes;
    }

    public static String createReportFilename(String reportName, ContentType contentType) {
        String filename = "";
        switch (contentType) {
            case XML:
                filename = reportName + ".xml";
                break;
            case PDF:
                filename = reportName + ".pdf";
                break;
        }
        return filename;
    }
}
