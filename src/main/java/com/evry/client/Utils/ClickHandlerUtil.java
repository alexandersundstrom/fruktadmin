package com.evry.client.Utils;

import com.evry.client.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.RootPanel;

public class ClickHandlerUtil {
    private SelectElement limitElement;
    private int offset = 0;
    private int limit = 10;
    private final String DOWNLOAD = "download";
    private final String UPLOAD = "upload";
    private DivElement contentDiv = DivElement.as(Document.get().getElementById("spar-content"));
    private fruktadminServiceAsync fruktkorgServiceRPC = GWT.create(fruktadminService.class);
    private ClientReportsTable reportTable;
    private Anchor backAnchor;
    private Anchor nextAnchor;
    private HandlerRegistration backHandler;
    private HandlerRegistration nextHandler;

    private ClickHandler back;
    private ClickHandler next;



    public ClickHandler getReport = event -> {

        back = backEvent -> {
            if (reportTable.showsLast()) {
                nextHandler = nextAnchor.addClickHandler(next);
                nextAnchor.removeStyleName("disabled");
            }
            limit = Integer.valueOf(limitElement.getValue());
            if (offset != 0) {
                offset = Math.max(0, offset - limit);
                reportTable.updateTable(limit, offset);
            }
            if (reportTable.showsFirst()) {
                backHandler.removeHandler();
                backAnchor.addStyleName("disabled");
            }
        };

        next  = nextEvent -> {
            if (reportTable.showsFirst()) {
                backHandler = backAnchor.addClickHandler(back);
                backAnchor.removeStyleName("disabled");
            }
            limit = Integer.valueOf(limitElement.getValue());
            offset = offset + limit;
            reportTable.updateTable(limit, offset);


            if (reportTable.showsLast()) {
                nextHandler.removeHandler();
                nextAnchor.addStyleName("disabled");
            }
        };

        contentDiv.setInnerHTML(Resources.INSTANCE.getReport().getText());
        limitElement = SelectElement.as(Document.get().getElementById("limit"));

        backAnchor = Anchor.wrap(Document.get().getElementById("back"));


        nextAnchor = Anchor.wrap(Document.get().getElementById("next"));
        nextHandler = nextAnchor.addClickHandler(next);

        switchNavbar(DOWNLOAD);
        Glass.on("Laddar...");
        reportTable = new ClientReportsTable("reportTable", limit, offset);
        fruktkorgServiceRPC.getReports(reportTable);
    };

    public ClickHandler uploadXML = event -> {
        contentDiv.setInnerHTML(Resources.INSTANCE.uploadXML().getText());
        switchNavbar(UPLOAD);

    };

    private void switchNavbar(String page) {
        switch (page) {
            case DOWNLOAD:
                RootPanel.get("menu_load").addStyleName("active");
                RootPanel.get("menu_upload").removeStyleName("active");
                break;
            case UPLOAD:
                RootPanel.get("menu_upload").addStyleName("active");
                RootPanel.get("menu_load").removeStyleName("active");
                break;
        }
    }
}
