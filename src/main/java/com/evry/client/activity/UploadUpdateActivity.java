package com.evry.client.activity;

import com.evry.client.core.ClientFactory;
import com.evry.client.page.UploadXMLPage;
import com.evry.client.place.UploadUpdatePlace;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class UploadUpdateActivity extends AbstractActivity implements FruktActivity {
    private ClientFactory clientFactory;

    public UploadUpdateActivity(UploadUpdatePlace place, ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
        UploadXMLPage uploadXMLPage = clientFactory.getUploadXMLPage();
        uploadXMLPage.setFruktActivity(this);
        containerWidget.setWidget(uploadXMLPage);
    }

    @Override
    public void goTo(Place place) {
        clientFactory.getPlaceController().goTo(place);
    }
}
