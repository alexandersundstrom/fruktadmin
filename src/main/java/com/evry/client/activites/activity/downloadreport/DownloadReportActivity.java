package com.evry.client.activites.activity.downloadreport;

import com.evry.client.activites.common.FruktActivity;
import com.evry.client.activites.common.ClientFactory;
import com.evry.client.activites.activity.downloadreport.page.DownloadReportPage;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class DownloadReportActivity extends AbstractActivity implements FruktActivity {
    private ClientFactory clientFactory;

    private int page;
    private int reportsPerPage;

    public DownloadReportActivity(DownloadReportPlace place, ClientFactory clientFactory) {
        page = place.getPage();
        reportsPerPage = place.getReportsPerPage();
        this.clientFactory = clientFactory;
    }

    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
        DownloadReportPage downloadReportPage = clientFactory.getDownloadReportPage();
        downloadReportPage.setFruktActivity(this);
        downloadReportPage.setTableProps(page, reportsPerPage);
        containerWidget.setWidget(downloadReportPage);
    }

    @Override
    public void goTo(Place place) {
        clientFactory.getPlaceController().goTo(place);
    }
}
