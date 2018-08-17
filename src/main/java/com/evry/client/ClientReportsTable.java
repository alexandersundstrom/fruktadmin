package com.evry.client;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.ListDataProvider;

import java.util.List;

public class ClientReportsTable implements AsyncCallback<List<ClientReport>> {
    private String id;

    public ClientReportsTable(String id) {
        this.id = id;
    }

    public void onSuccess(List<ClientReport> result) {
        CellTable<ClientReport> table = createReportsTable(result);
        RootPanel.get(id).add(table);
        Glass.off();
    }

    public void onFailure(Throwable throwable) {
    }

    private CellTable<ClientReport> createReportsTable(List<ClientReport> clientReports) {
        CellTable<ClientReport> table = new CellTable<>();

        TextColumn<ClientReport> nameColumn = new TextColumn<ClientReport>() {
            @Override
            public String getValue(ClientReport clientReport) {
                String location = clientReport.getLocation();
                return location.substring(location.lastIndexOf("t") + 2);
            }
        };

        table.addColumn(nameColumn, "Namn");

        TextColumn<ClientReport> createdColumn = new TextColumn<ClientReport>() {
            @Override
            public String getValue(ClientReport clientReport) {
                return clientReport.getCreated();
            }
        };

        table.addColumn(createdColumn, "Skapad");

        Column<ClientReport, String> downloadReportPDF = new Column<ClientReport, String>(new ButtonCell()) {
            @Override
            public String getValue(ClientReport clientReport) {
                return "PDF";
            }

            @Override
            public void onBrowserEvent(Cell.Context context, Element elem, ClientReport clientReport, NativeEvent event) {
                event.preventDefault();
                Cookies.setCookie("REPORT_ID", clientReport.getId() + "");
                Cookies.setCookie("REPORT_FILE_TYPE", "PDF");
                Window.open(GWT.getModuleBaseURL() + "downloadReport", "_self", "enabled");
            }
        };

        table.addColumn(downloadReportPDF, "PDF");

        Column<ClientReport, String> downloadReportXML = new Column<ClientReport, String>(new ButtonCell()) {
            @Override
            public String getValue(ClientReport clientReport) {
                return "XML";
            }

            @Override
            public void onBrowserEvent(Cell.Context context, Element elem, ClientReport clientReport, NativeEvent event) {
                event.preventDefault();
                Cookies.setCookie("REPORT_ID", clientReport.getId() + "");
                Cookies.setCookie("REPORT_FILE_TYPE", "XML");
                Window.open(GWT.getModuleBaseURL() + "downloadReport", "_self", "enabled");
            }
        };

        table.addColumn(downloadReportXML, "XML");

        ListDataProvider<ClientReport> dataProvider = new ListDataProvider<>();
        dataProvider.addDataDisplay(table);

        List<ClientReport> list = dataProvider.getList();
        list.addAll(clientReports);

        table.addStyleName("fruktTable");

        return table;
    }
}
