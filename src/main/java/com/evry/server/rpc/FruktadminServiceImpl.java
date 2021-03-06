package com.evry.server.rpc;

import com.evry.client.activites.activity.downloadreport.widget.model.ClientReport;
import com.evry.client.rpc.FruktadminService;
import com.evry.fruktkorgservice.ReportService;
import com.evry.fruktkorgservice.model.ImmutableReport;
import com.evry.server.Beans;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class FruktadminServiceImpl extends RemoteServiceServlet implements FruktadminService {

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