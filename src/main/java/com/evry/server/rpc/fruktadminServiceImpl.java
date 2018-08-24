package com.evry.server.rpc;

import com.evry.client.model.ClientReport;
import com.evry.client.rpc.fruktadminService;
import com.evry.fruktkorgservice.model.ImmutableReport;
import com.evry.fruktkorgservice.service.ReportService;
import com.evry.server.util.Beans;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class fruktadminServiceImpl extends RemoteServiceServlet implements fruktadminService {

    @Override
    public List<ClientReport> getReports() {
        ReportService reportService = Beans.getBean("reportService");
        List<ImmutableReport> immutableReports = reportService.listReports();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM HH:mm").withLocale(Locale.forLanguageTag("sv-SE")).withZone(ZoneId.systemDefault());

        return immutableReports
                .stream()
                .map(ir -> {
                    ClientReport clientReport = new ClientReport();
                    clientReport.setId(ir.getId());
                    clientReport.setLocation(ir.getLocation());
                    clientReport.setRead(ir.isRead());
                    clientReport.setCreated(dateTimeFormatter.format(ir.getCreated()));
                    return clientReport;
                }).collect((Collectors.toList()));
    }
}