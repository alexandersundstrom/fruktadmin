package com.evry.client.Utils;

import com.evry.client.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;

public class ClickHandlerUtil {
    private ListBox limitElement;
    private int offset = 0;
    private int limit = 10;
    private final String DOWNLOAD = "download";
    private final String UPLOAD = "upload";
    private DivElement contentDiv = DivElement.as(Document.get().getElementById("spar-content"));
    private DivElement pageInfo;
    private fruktadminServiceAsync fruktkorgServiceRPC = GWT.create(fruktadminService.class);
    private ClientReportsTable reportTable;
    private Anchor backAnchor;
    private Anchor nextAnchor;
    private HandlerRegistration backHandler;
    private HandlerRegistration nextHandler;
    ChangeHandler limitChangeHandler;

    private ClickHandler back;
    private ClickHandler next;


    public ClickHandler getReport = event -> {

        initNavigationHandlers();

        contentDiv.setInnerHTML(Resources.INSTANCE.getReport().getText());
        limitElement = ListBox.wrap(Document.get().getElementById("limit"));
        limitElement.addChangeHandler(limitChangeHandler);

        pageInfo = DivElement.as(Document.get().getElementById("page-info"));

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

    private void enableBackNavigation() {
        backHandler = backAnchor.addClickHandler(back);
        backAnchor.removeStyleName("disabled");
    }

    private void disableBackNavigation() {
        backHandler.removeHandler();
        backAnchor.addStyleName("disabled");
    }

    private void enableNextNavigation() {
        nextHandler = nextAnchor.addClickHandler(next);
        nextAnchor.removeStyleName("disabled");
    }

    private void disableNextNavigation() {
        nextHandler.removeHandler();
        nextAnchor.addStyleName("disabled");
    }

    private void initNavigationHandlers() {
        back = backEvent -> {
            limit = Integer.valueOf(limitElement.getSelectedValue());
            if (offset != 0) {
                offset = Math.max(0, offset - limit);
                reportTable.updateTable(limit, offset);
                updatePageDisplay();
            }
            if (reportTable.showsFirst()) {
                disableBackNavigation();
            }
            if (!reportTable.showsLast()) {
                enableNextNavigation();
            }
        };

        next  = nextEvent -> {
            if (reportTable.showsFirst()) {
                enableBackNavigation();
            }
            if (!reportTable.showsLast()) {
                limit = Integer.valueOf(limitElement.getSelectedValue());
                offset = offset + limit;
                reportTable.updateTable(limit, offset);
                updatePageDisplay();
            }

            if (reportTable.showsLast()) {
                disableNextNavigation();
            }
        };

        limitChangeHandler = limitChange -> {
            limit = Integer.valueOf(limitElement.getSelectedValue());
            reportTable.updateTable(limit, offset);
            updatePageDisplay();

            if (reportTable.showsLast()) {
                disableNextNavigation();
            } else {
                enableNextNavigation();
            }
        };
    }

    private void updatePageDisplay() {
        pageInfo.setInnerHTML("Rapporter " + (offset + 1) + "-" + (Math.min(offset + limit, reportTable.count()) + " av " + reportTable.count()));
    }

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
