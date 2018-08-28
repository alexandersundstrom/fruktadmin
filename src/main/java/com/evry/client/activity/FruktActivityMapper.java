package com.evry.client.activity;

import com.evry.client.core.ClientFactory;
import com.evry.client.place.DownloadReportPlace;
import com.evry.client.place.FeedbackPlace;
import com.evry.client.place.HomePlace;
import com.evry.client.place.UploadUpdatePlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class FruktActivityMapper implements ActivityMapper {
    private ClientFactory clientFactory;

    public FruktActivityMapper(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    @Override
    public Activity getActivity(Place place) {
        if(place instanceof DownloadReportPlace) {
            return new DownloadReportActivity((DownloadReportPlace) place, clientFactory);
        }
        if(place instanceof HomePlace) {
            return new HomeActivity((HomePlace) place, clientFactory);
        }
        if(place instanceof UploadUpdatePlace) {
            return new UploadUpdateActivity((UploadUpdatePlace) place, clientFactory);
        }
        if(place instanceof FeedbackPlace) {
            return new FeedbackActivity((FeedbackPlace) place, clientFactory);
        }

        return null;
    }
}
