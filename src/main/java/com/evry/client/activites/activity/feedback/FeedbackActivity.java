package com.evry.client.activites.activity.feedback;

import com.evry.client.activites.common.FruktActivity;
import com.evry.client.activites.common.ClientFactory;
import com.evry.client.activites.activity.feedback.page.FeedbackPage;
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
        feedbackPage.reset();
        containerWidget.setWidget(feedbackPage);
    }

    @Override
    public String mayStop() {
        return clientFactory.getFeedbackPage().isDirty() ? "Du har osparade ändringar, är du säker på att du vill lämna sidan?" : null;
    }

    @Override
    public void goTo(Place place) {
        clientFactory.getPlaceController().goTo(place);
    }
}
