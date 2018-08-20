package com.evry.client;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.ListDataProvider;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClientReportsTable implements AsyncCallback<List<ClientReport>> {
    private String id;
    private int limit;
    private int offset;
    private List<ClientReport> clientReports;

    public ClientReportsTable(String id,int limit, int offset) {
        this.id = id;
        this.limit = limit;
        this.offset = offset;
    }

    public void onSuccess(List<ClientReport> result) {
        clientReports = result;
        Collections.sort(result);
        CellTable<ClientReport> table = createReportsTable(clientReports.subList(offset, Math.min(clientReports.size(), offset + limit)));
        RootPanel content = RootPanel.get(id);
        content.getElement().setInnerHTML("");
        content.add(table);
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

        nameColumn.setSortable(true);

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
        table.setVisibleRange(0, limit);

        ColumnSortEvent.ListHandler<ClientReport> nameSortHandler = new ColumnSortEvent.ListHandler<>(list);
        table.addColumnSortHandler(nameSortHandler);
        nameSortHandler.setComparator(nameColumn, Comparator.comparing(ClientReport::getLocation));

        return table;
    }

    public void updateTable(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
        CellTable<ClientReport> table = createReportsTable(clientReports.subList(this.offset, Math.min(clientReports.size(), this.offset + this.limit)));
        RootPanel content = RootPanel.get(id);
        content.getElement().setInnerHTML("");
        content.add(table);

    }

    public boolean showsFirst() {
       return offset == 0;
    }

    public boolean showsLast() {
        return offset + limit >= clientReports.size();
    }

    public int count() {
        return clientReports.size();
    }
}
