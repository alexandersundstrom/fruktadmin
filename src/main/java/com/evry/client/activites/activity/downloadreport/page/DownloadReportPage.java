package com.evry.client.activites.activity.downloadreport.page;

import com.evry.client.activites.activity.FruktActivity;
import com.evry.client.activites.activity.downloadreport.widget.model.ClientReport;
import com.evry.client.rpc.FruktadminService;
import com.evry.client.rpc.FruktadminServiceAsync;
import com.evry.client.util.Log;
import com.evry.client.activites.activity.downloadreport.widget.table.ClientReportTableWidget;
import com.evry.client.activites.dependencies.widgets.glass.GlassWidget;
import com.evry.client.activites.dependencies.widgets.maincontent.MainContentWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import java.util.List;

public class DownloadReportPage extends Composite {
    interface MyUiBinder extends UiBinder<Widget, DownloadReportPage> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField
    MainContentWidget mainContent;

    @UiField
    ClientReportTableWidget reportTable;

    @UiField
    GlassWidget glass;

    public DownloadReportPage() {
        initWidget(uiBinder.createAndBindUi(this));

        mainContent.setText("Här kan du ladda ner alla fruktkorgar. När en rapport har laddats ner tas den bort följande natt.");

        glass.on("Laddar...");
        FruktadminServiceAsync fruktkorgServiceRPC = GWT.create(FruktadminService.class);
        fruktkorgServiceRPC.getReports(new AsyncCallback<List<ClientReport>>() {
            @Override
            public void onFailure(Throwable caught) {
                Log.error("Error while fetching client reports: " + caught.getMessage());
                glass.off();
            }

            @Override
            public void onSuccess(List<ClientReport> result) {
                reportTable.init(result);
                glass.off();
            }
        });
    }

    public void setTableProps(int page, int reportsPerPage) {
        reportTable.setItemsPerPage(reportsPerPage);
        reportTable.goToPage(page);
        reportTable.initPagination();
    }

    public void setFruktActivity(FruktActivity fruktActivity) {
        mainContent.setFruktActivity(fruktActivity);
    }
}
