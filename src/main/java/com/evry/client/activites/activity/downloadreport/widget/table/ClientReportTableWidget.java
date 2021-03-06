package com.evry.client.activites.activity.downloadreport.widget.table;

import com.evry.client.activites.activity.downloadreport.widget.model.ClientReport;
import com.evry.client.activites.activity.downloadreport.widget.table.pagination.PaginationWidget;
import com.evry.client.activites.common.css.StylingCss;
import com.evry.client.activites.activity.downloadreport.widget.table.pagination.Pageable;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

import java.util.Comparator;
import java.util.List;

public class ClientReportTableWidget extends Composite implements Pageable {
    interface MyUiBinder extends UiBinder<Widget, ClientReportTableWidget> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    private int currentPage;
    private int reportsPerPage;
    private List<ClientReport> clientReports;
    private boolean sortAscending;

    @UiField
    CellTable<ClientReport> reportTable;

    @UiField
    StylingCss style;

    @UiField
    PaginationWidget pagination;

    public ClientReportTableWidget() {
        currentPage = 1;
        reportsPerPage = 10;

        initWidget(uiBinder.createAndBindUi(this));
    }

    public void init(List<ClientReport> reports) {
        clientReports = reports;

        initReportTable();
        refreshTable();
        initPagination();
    }

    public void initPagination() {
        pagination.init(this);
    }

    private void initReportTable() {
        TextColumn<ClientReport> nameColumn = new TextColumn<ClientReport>() {
            @Override
            public String getValue(ClientReport clientReport) {
                return getLocationSubstringed(clientReport);
            }
        };

        ClickableArrowHeaderCell clickableNameSortingCell = new ClickableArrowHeaderCell(sortAscending, style.arrowHeader(), style.arrowImage());
        Header<String> nameHeader = new Header<String>(clickableNameSortingCell) {
            @Override
            public String getValue() {
                return "Namn";
            }
        };

        nameHeader.setUpdater(value -> {
            clickableNameSortingCell.toggleSorting();
            sortAscending = clickableNameSortingCell.isAscending();
            sortReports();
            refreshTable();
            reportTable.redrawHeaders();
        });

        reportTable.addColumn(nameColumn, nameHeader);
        TextColumn<ClientReport> createdColumn = new TextColumn<ClientReport>() {
            @Override
            public String getValue(ClientReport clientReport) {
                return clientReport.getCreated();
            }
        };

        reportTable.addColumn(createdColumn, "Skapad");

        Column<ClientReport, String> downloadReportPDF = new Column<ClientReport, String>(new ButtonCell()) {
            @Override
            public String getValue(ClientReport clientReport) {
                return "PDF";
            }

            @Override
            public void onBrowserEvent(Cell.Context context, Element elem, ClientReport clientReport, NativeEvent event) {
                event.preventDefault();
                String reportName = getLocationSubstringed(clientReport);
                String url = getUrl(clientReport.getId(), reportName, "PDF");
                Window.open(url, "_self", "enabled");
            }
        };

        reportTable.addColumn(downloadReportPDF, "PDF");

        Column<ClientReport, String> downloadReportXML = new Column<ClientReport, String>(new ButtonCell()) {
            @Override
            public String getValue(ClientReport clientReport) {
                return "XML";
            }

            @Override
            public void onBrowserEvent(Cell.Context context, Element elem, ClientReport clientReport, NativeEvent event) {
                event.preventDefault();
                String reportName = getLocationSubstringed(clientReport);
                String url = getUrl(clientReport.getId(), reportName, "XML");
                Window.open(url, "_self", "enabled");
            }
        };

        reportTable.addColumn(downloadReportXML, "XML");
    }

    private String getUrl(long id, String name, String fileType) {
        return GWT.getModuleBaseURL() + "downloadReport?name=" + name + "&id=" + id + "&type=" + fileType;
    }

    private String getLocationSubstringed(ClientReport clientReport) {
        String location = clientReport.getLocation();
        location = location.substring(location.lastIndexOf("t") + 2);
        location = location.substring(0, location.lastIndexOf("."));

        return location;
    }

    private void setRows(List<ClientReport> clientReports) {
        ListDataProvider<ClientReport> dataProvider = new ListDataProvider<>();
        dataProvider.addDataDisplay(reportTable);

        List<ClientReport> list = dataProvider.getList();
        list.addAll(clientReports);

        reportTable.addStyleName(style.fruktTable());
        reportTable.setVisibleRange(0, reportsPerPage);
    }

    private void refreshTable() {
        if(clientReports == null) {
            return;
        }
        setRows(clientReports.subList((currentPage - 1) * reportsPerPage, Math.min(clientReports.size(), currentPage * reportsPerPage)));
    }

    private void sortReports() {
        if(clientReports == null) {
            return;
        }
        if(sortAscending) {
            clientReports.sort(Comparator.naturalOrder());
        } else {
            clientReports.sort(Comparator.reverseOrder());
        }
    }

    @Override
    public void goToPage(int page) {
        if(page == currentPage || page < 1 || page > getLastPage()) {
            return;
        }

        currentPage = page;

        refreshTable();
    }

    @Override
    public void goToLastPage() {
        goToPage(getLastPage());
    }

    @Override
    public void goToNextPage() {
        goToPage(currentPage + 1);
    }

    @Override
    public void goToPreviousPage() {
        goToPage(currentPage - 1);
    }

    @Override
    public void goToFirstPage() {
        goToPage(1);
    }

    @Override
    public void setItemsPerPage(int itemsPerPage) {
        reportsPerPage = itemsPerPage;
    }

    @Override
    public int getItemsPerPage() {
        return reportsPerPage;
    }

    @Override
    public String getItemName() {
        return "rapporter";
    }

    @Override
    public int getFirstPage() {
        return 1;
    }

    @Override
    public int getLastPage() {
        if(clientReports == null) {
            return 0;
        }
        return clientReports.size() % reportsPerPage == 0 ? clientReports.size() / reportsPerPage : (clientReports.size() / reportsPerPage) + 1;
    }

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public int getItemCount() {
        return clientReports != null ? clientReports.size() : 0;
    }

    @Override
    public void refresh() {
        refreshTable();
    }
}
