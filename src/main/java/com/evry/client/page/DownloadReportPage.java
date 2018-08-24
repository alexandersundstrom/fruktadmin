package com.evry.client.page;

import com.evry.client.model.ClientReport;
import com.evry.client.rpc.fruktadminService;
import com.evry.client.rpc.fruktadminServiceAsync;
import com.evry.client.util.Log;
import com.evry.client.widget.ClientReportTableWidget;
import com.evry.client.widget.GlassWidget;
import com.evry.client.widget.MainContentWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.List;

public class DownloadReportPage extends Composite {
    interface MyUiBinder extends UiBinder<Widget, DownloadReportPage> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField
    MainContentWidget mainContent;

    @UiField
    SimplePanel reportTable;

    @UiField
    GlassWidget glass;

    public DownloadReportPage() {
        initWidget(uiBinder.createAndBindUi(this));

        mainContent.setText("Här kan du ladda ner alla fruktkorgar. När en rapport har laddats ner tas den bort följande natt.");

        glass.on("Laddar...");
        fruktadminServiceAsync fruktkorgServiceRPC = GWT.create(fruktadminService.class);
        fruktkorgServiceRPC.getReports(new AsyncCallback<List<ClientReport>>() {
            @Override
            public void onFailure(Throwable caught) {
                Log.error("Error while fetching client reports: " + caught.getMessage());
                glass.off();
            }

            @Override
            public void onSuccess(List<ClientReport> result) {
                ClientReportTableWidget tableWidget = new ClientReportTableWidget(result);
                reportTable.add(tableWidget);
                glass.off();
            }
        });
    }
}
