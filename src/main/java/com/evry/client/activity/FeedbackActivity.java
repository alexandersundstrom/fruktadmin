package com.evry.client.activity;

import com.evry.client.core.ClientFactory;
import com.evry.client.page.FeedbackPage;
import com.evry.client.place.FeedbackPlace;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class FeedbackActivity extends AbstractActivity implements FruktActivity {
    private ClientFactory clientFactory;

    public FeedbackActivity(FeedbackPlace place, ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
        FeedbackPage feedbackPage = clientFactory.getFeedbackPage();
        feedbackPage.setFruktActivity(this);
        containerWidget.setWidget(feedbackPage);
    }

    @Override
    public void goTo(Place place) {
        clientFactory.getPlaceController().goTo(place);
    }
}