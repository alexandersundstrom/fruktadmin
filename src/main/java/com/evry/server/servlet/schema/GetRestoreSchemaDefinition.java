package com.evry.server.servlet.schema;

import com.evry.fruktkorgservice.ReportService;
import com.evry.server.servlet.download.model.FileInformationHolder;
import com.evry.server.servlet.util.FileUtil;
import com.evry.server.servlet.util.ResponseUtil;
import com.evry.server.util.Beans;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetRestoreSchemaDefinition extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ReportService reportService = Beans.getBean("reportService");

        byte[] schemaAsBytes = IOUtils.toByteArray(reportService.getRestoreXSD());
        if (schemaAsBytes != null) {
            String filename = "restore.xsd";
            FileInformationHolder fileHolder = new FileInformationHolder(schemaAsBytes, filename, FileUtil.ContentType.XSD.getContentType());
            ResponseUtil.sendFile(fileHolder, resp);
        }
    }
}
