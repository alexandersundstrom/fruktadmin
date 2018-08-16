package com.evry.server;

import com.evry.client.fruktadminService;
import com.evry.fruktkorgservice.service.ReportService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class fruktadminServiceImpl extends RemoteServiceServlet implements fruktadminService {
    // Implementation of sample interface method
    public String getMessage(String msg) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        ReportService reportService = (ReportService) context.getBean("reportService");
        return "Client said: \"" + msg + "\"<br>Server answered: \"" + reportService.listReports() + "!\"";
    }
}