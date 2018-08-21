package com.evry.client.Utils;

import com.evry.client.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

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

    private Anchor firstAnchor;
    private HandlerRegistration firstHandler;
    private ClickHandler first;

    private Anchor backAnchor;
    private HandlerRegistration backHandler;
    private ClickHandler back;

    private Anchor nextAnchor;
    private HandlerRegistration nextHandler;
    private ChangeHandler limitChangeHandler;
    private ClickHandler next;

    private Anchor lastAnchor;
    private HandlerRegistration lastHandler;
    private ClickHandler last;



    public ClickHandler getReport = event -> {

        initNavigationHandlers();

        contentDiv.setInnerHTML(Resources.INSTANCE.getReport().getText());
        limitElement = ListBox.wrap(Document.get().getElementById("limit"));
        limitElement.addChangeHandler(limitChangeHandler);

        pageInfo = DivElement.as(Document.get().getElementById("page-info"));

        firstAnchor = Anchor.wrap(Document.get().getElementById("first"));
        backAnchor = Anchor.wrap(Document.get().getElementById("back"));

        nextAnchor = Anchor.wrap(Document.get().getElementById("next"));
        nextHandler = nextAnchor.addClickHandler(next);

        lastAnchor = Anchor.wrap(Document.get().getElementById("last"));
        lastHandler = lastAnchor.addClickHandler(last);

        switchNavbar(DOWNLOAD);
        Glass.on("Laddar...");
        reportTable = new ClientReportsTable("reportTable", limit, offset, true);
        fruktkorgServiceRPC.getReports(reportTable);
    };

    public ClickHandler uploadXML = event -> {
        contentDiv.setInnerHTML(Resources.INSTANCE.uploadXML().getText());
        switchNavbar(UPLOAD);

        // Create a FormPanel and point it at a service.
        final FormPanel form = new FormPanel();
        form.setAction("/fruktadmin/upploadXML");

        // Because we're going to add a FileUpload widget, we'll need to set the
        // form to use the POST method, and multipart MIME encoding.
        form.setEncoding(FormPanel.ENCODING_MULTIPART);
        form.setMethod(FormPanel.METHOD_POST);

        // Create a panel to hold all of the form widgets.
        VerticalPanel panel = new VerticalPanel();
        form.setWidget(panel);

        // Create a FileUpload widget.
        FileUpload upload = new FileUpload();
        upload.setName("uploadFormElement");
        upload.getElement().setAttribute("accept", ".xml");
        upload.setStyleName("margin-bottom");
        panel.add(upload);

        // Add a 'submit' button.
        panel.add(new Button("Submit", (ClickHandler) clickEvent -> {
            form.submit();
        }));

        // Add an event handler to the form.
        form.addSubmitHandler(submitEvent -> {
            // This event is fired just before the form is submitted. We can take
            // this opportunity to perform validation.
            Glass.on("Laddar Upp...");

        });
        form.addSubmitCompleteHandler(completeEvent -> {
            // When the form submission is successfully completed, this event is
            // fired. Assuming the service returned a response of type text/html,
            // we can get the result text here (see the FormPanel documentation for
            // further explanation).
            Glass.off();
            Window.alert(completeEvent.getResults());
        });

        RootPanel.get("spar-inner-content").add(form);
    };

    private void disableFirstNavigation() {
        firstHandler.removeHandler();
        firstAnchor.addStyleName("disabled");
    }

    private void enableFirstNavigation() {
        firstAnchor.removeStyleName("disabled");
        firstHandler = firstAnchor.addClickHandler(first);
    }

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

    private void enableLastNavigation() {
        lastHandler = lastAnchor.addClickHandler(last);
        lastAnchor.removeStyleName("disabled");
    }

    private void disableLastNavigation() {
        lastHandler.removeHandler();
        lastAnchor.addStyleName("disabled");
    }


    private void initNavigationHandlers() {

        first = firstEvent -> {
            boolean wasOnLastPageBeforeClick = reportTable.showsLast();

            if (!reportTable.showsFirst()) {
                offset = 0;
                reportTable.updateTable(limit, offset);
                updatePageDisplay();
                disableBackNavigation();
                disableFirstNavigation();
                if (!reportTable.showsLast() && wasOnLastPageBeforeClick) {
                    enableNextNavigation();
                    enableLastNavigation();
                }
            }
        };

        back = backEvent -> {
            boolean wasOnLastPageBeforeClick = reportTable.showsLast();
            limit = Integer.valueOf(limitElement.getSelectedValue());
            if (offset != 0) {
                offset = Math.max(0, offset - limit);
                reportTable.updateTable(limit, offset);
                updatePageDisplay();

                if (reportTable.showsFirst()) {
                    disableBackNavigation();
                    disableFirstNavigation();
                }
                if (!reportTable.showsLast() && wasOnLastPageBeforeClick) {
                    enableNextNavigation();
                    enableLastNavigation();
                }
            }
        };

        next = nextEvent -> {
            boolean wasOnFirstPageBeforeClick = reportTable.showsFirst();
            if (!reportTable.showsLast()) {
                limit = Integer.valueOf(limitElement.getSelectedValue());
                offset = offset + limit;
                reportTable.updateTable(limit, offset);
                updatePageDisplay();

                if (!reportTable.showsFirst() && wasOnFirstPageBeforeClick) {
                    enableBackNavigation();
                    enableFirstNavigation();
                }

                if (reportTable.showsLast()) {
                    disableNextNavigation();
                    disableLastNavigation();
                }
            }
        };

        last = lastEvent -> {
            if (!reportTable.showsLast()) {
                boolean wasShowingFirst = reportTable.showsFirst();
                int reportsOnLastPage = reportTable.count() % limit;
                offset = reportsOnLastPage == 0 ? limit: reportTable.count() -reportsOnLastPage;
                reportTable.updateTable(limit, offset);
                updatePageDisplay();

                if (!reportTable.showsFirst() && wasShowingFirst) {
                    enableBackNavigation();
                    enableFirstNavigation();
                }
                disableNextNavigation();
                disableLastNavigation();
            }
        };

        limitChangeHandler = limitChange -> {
            limit = Integer.valueOf(limitElement.getSelectedValue());
            if (limit == 0) {
                offset = 0;
                limit = reportTable.count();
            }
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
