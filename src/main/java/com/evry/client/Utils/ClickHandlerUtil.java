package com.evry.client.Utils;

import com.evry.client.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.RootPanel;

public class ClickHandlerUtil {
    private static final String DOWNLOAD = "download";
    private static final String UPLOAD = "uppload";
    private static DivElement contentDiv = DivElement.as(Document.get().getElementById("spar-content"));
    private static fruktadminServiceAsync fruktkorgServiceRPC = GWT.create(fruktadminService.class);

    public static ClickHandler getReport = event -> {
        contentDiv.setInnerHTML(Resources.INSTANCE.getReport().getText());
        switchNavbar(DOWNLOAD);
        Glass.on("Laddar...");
        fruktkorgServiceRPC.getReports(10, 0, new ClientReportsTable("reportTable"));
    };

    public static ClickHandler uploadXML = event -> {
        contentDiv.setInnerHTML(Resources.INSTANCE.uploadXML().getText());
        switchNavbar(UPLOAD);

    };

    private static void switchNavbar(String page) {
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
