package com.evry.server.servlet.schema;

import com.evry.fruktkorgservice.ReportService;
import com.evry.server.Beans;
import com.evry.server.servlet.common.ContentType;
import com.evry.server.servlet.common.util.ResponseUtil;
import com.evry.server.servlet.download.model.FileInformationHolder;
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
            FileInformationHolder fileHolder = new FileInformationHolder(schemaAsBytes, filename, ContentType.XSD.getContentType());
            ResponseUtil.sendFile(fileHolder, resp);
        }
    }
}
