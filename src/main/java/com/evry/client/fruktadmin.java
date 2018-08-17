package com.evry.client;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.*;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.ListDataProvider;

import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class fruktadmin implements EntryPoint {
    private static final String DOWNLOAD = "download";
    private static final String UPLOAD = "uppload";
    private DivElement contentDiv = DivElement.as(Document.get().getElementById("spar-content"));
    private LIElement downloadLi = LIElement.as(Document.get().getElementById("menu_load"));
    private LIElement uploadLi = LIElement.as(Document.get().getElementById("menu_upload"));
    fruktadminServiceAsync messageThing = GWT.create(fruktadminService.class);

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        Anchor downloadreportAnchor = Anchor.wrap(Document.get().getElementById("downloadReport"));
        downloadreportAnchor.addClickHandler(getReport);

        Anchor downloadreportAnchorNav = Anchor.wrap(Document.get().getElementById("downloadReportNav"));
        downloadreportAnchorNav.addClickHandler(getReport);

        Anchor uploadReportAnchor = Anchor.wrap(Document.get().getElementById("uploadReport"));
        uploadReportAnchor.addClickHandler(uploadXML);

        Anchor uploadReportAnchorNav = Anchor.wrap(Document.get().getElementById("uploadReportNav"));
        uploadReportAnchorNav.addClickHandler(uploadXML);
    }

    private static class MyAsyncCallback implements AsyncCallback<List<ClientReport>> {
        private DivElement div;
        private ClientReport clientReport;

        MyAsyncCallback(DivElement div) {
            this.div = div;
        }

        public void onSuccess(List<ClientReport> result) {
            CellTable<ClientReport> table = new CellTable<>();

            Column<ClientReport, String> uploadReport = new Column<ClientReport, String>(new ButtonCell()) {
                @Override
                public String getValue(ClientReport clientReport) {
                    String location = clientReport.getLocation();
                    location = location.substring(location.lastIndexOf("t") + 2);
                    return location;
                }

                @Override
                public void onBrowserEvent(Cell.Context context, Element elem, ClientReport clientReport, NativeEvent event) {
                    event.preventDefault();
                    Cookies.setCookie("REPORT_ID", clientReport.getId() + "");
                    Window.open(GWT.getModuleBaseURL() + "downloadReport", "_self", "enabled");
                }
            };

            table.addColumn(uploadReport, "Fil");

            TextColumn<ClientReport> createdColumn = new TextColumn<ClientReport>() {
                @Override
                public String getValue(ClientReport clientReport) {
                    return clientReport.getCreated();
                }
            };

            table.addColumn(createdColumn, "Skapad");
            ListDataProvider<ClientReport> dataProvider = new ListDataProvider<>();
            dataProvider.addDataDisplay(table);

            List<ClientReport> list = dataProvider.getList();
            list.addAll(result);

            RootPanel.get("reportTable").add(table);
            Glass.off();
        }

        public void onFailure(Throwable throwable) {
        }
    }

    private ClickHandler getReport = event -> {
        contentDiv.setInnerHTML(Resources.INSTANCE.getReport().getText());
        switchContent(DOWNLOAD);
        Glass.on("Laddar...");
        messageThing.getReports(new MyAsyncCallback(contentDiv));
    };

    private ClickHandler uploadXML = event -> {
        contentDiv.setInnerHTML(Resources.INSTANCE.uploadXML().getText());
        switchContent(UPLOAD);

    };

    private void switchContent(String page) {
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
