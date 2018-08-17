package com.evry.client.Utils;

import com.evry.client.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.event.dom.client.ClickHandler;
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

    private ClickHandler next = event -> {
        limit = Integer.valueOf(limitElement.getValue());
        offset = offset + limit;
        reportTable.updateTable(limit, offset);
    };

    private ClickHandler back = event -> {
        limit = Integer.valueOf(limitElement.getValue());
        if (offset != 0) {
            offset = Math.max(0, offset - limit);
            reportTable.updateTable(limit, offset);
        }
    };

    public ClickHandler getReport = event -> {
        contentDiv.setInnerHTML(Resources.INSTANCE.getReport().getText());
        limitElement = SelectElement.as(Document.get().getElementById("limit"));

        Anchor backAnchor = Anchor.wrap(Document.get().getElementById("back"));
        backAnchor.addClickHandler(back);

        Anchor nextAnchor = Anchor.wrap(Document.get().getElementById("next"));
        nextAnchor.addClickHandler(next);

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
