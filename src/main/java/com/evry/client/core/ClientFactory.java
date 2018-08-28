package com.evry.client.core;

import com.evry.client.page.DownloadReportPage;
import com.evry.client.page.FeedbackPage;
import com.evry.client.page.HomePage;
import com.evry.client.page.UploadUpdatePage;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

public class ClientFactory {
    private final EventBus eventBus = new SimpleEventBus();
    private final PlaceController placeController = new PlaceController(eventBus);
    private final DownloadReportPage downloadReportPage = new DownloadReportPage();
    private final HomePage homePage = new HomePage();
    private final UploadUpdatePage uploadUpdatePage = new UploadUpdatePage();
    private final FeedbackPage feedbackPage = new FeedbackPage();

    public EventBus getEventBus() {
        return eventBus;
    }

    public PlaceController getPlaceController() {
        return placeController;
    }

    public DownloadReportPage getDownloadReportPage() {
        return downloadReportPage;
    }

    public HomePage getHomePage() {
        return homePage;
    }

    public UploadUpdatePage getUploadUpdatePage() {
        return uploadUpdatePage;
    }

    public FeedbackPage getFeedbackPage() {
        return feedbackPage;
    }
}
