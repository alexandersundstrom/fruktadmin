package com.evry.client.activity;

import com.evry.client.core.ClientFactory;
import com.evry.client.page.HomePage;
import com.evry.client.place.HomePlace;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class HomeActivity extends AbstractActivity implements FruktActivity {
    private ClientFactory clientFactory;

    public HomeActivity(HomePlace place, ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
        HomePage homePage = clientFactory.getHomePage();
        homePage.setFruktActivity(this);
        containerWidget.setWidget(homePage);
    }

    @Override
    public void goTo(Place place) {
        clientFactory.getPlaceController().goTo(place);
    }
}
