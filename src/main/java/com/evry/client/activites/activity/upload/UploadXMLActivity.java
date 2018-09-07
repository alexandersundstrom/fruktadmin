package com.evry.client.activites.activity.upload;

import com.evry.client.activites.activity.FruktActivity;
import com.evry.client.activites.activity.ClientFactory;
import com.evry.client.activites.activity.upload.page.UploadXMLPage;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class UploadXMLActivity extends AbstractActivity implements FruktActivity {
    private ClientFactory clientFactory;

    public UploadXMLActivity(UploadXMLPlace place, ClientFactory clientFactory) {
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
