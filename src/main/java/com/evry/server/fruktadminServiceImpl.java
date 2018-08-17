package com.evry.server;

import com.evry.client.ClientReport;
import com.evry.client.fruktadminService;
import com.evry.fruktkorgservice.model.ImmutableReport;
import com.evry.fruktkorgservice.service.ReportService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class fruktadminServiceImpl extends RemoteServiceServlet implements fruktadminService {

    @Override
    public List<ClientReport> getReports() {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        ReportService reportService = (ReportService) context.getBean("reportService");
        List<ImmutableReport> immutableReports = reportService.listReports();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM HH:mm").withLocale(Locale.forLanguageTag("sv-SE")).withZone(ZoneId.systemDefault());

        ArrayList<ClientReport> clientReports = new ArrayList<>(immutableReports
                .stream()
                .map(ir -> {
                    ClientReport clientReport = new ClientReport();
                    clientReport.setId(ir.getId());
                    clientReport.setLocation(ir.getLocation());
                    clientReport.setRead(ir.isRead());
                    clientReport.setCreated(dateTimeFormatter.format(ir.getCreated()));
                    return clientReport;
                }).collect((Collectors.toList())));
        return clientReports;
    }
}