package com.evry.client.activites.common;

import com.evry.client.activites.activity.downloadreport.DownloadReportActivity;
import com.evry.client.activites.activity.feedback.FeedbackActivity;
import com.evry.client.activites.activity.home.HomeActivity;
import com.evry.client.activites.activity.upload.UploadXMLActivity;
import com.evry.client.activites.activity.downloadreport.DownloadReportPlace;
import com.evry.client.activites.activity.feedback.FeedbackPlace;
import com.evry.client.activites.activity.home.HomePlace;
import com.evry.client.activites.activity.upload.UploadXMLPlace;
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
        if(place instanceof UploadXMLPlace) {
            return new UploadXMLActivity((UploadXMLPlace) place, clientFactory);
        }
        if(place instanceof FeedbackPlace) {
            return new FeedbackActivity((FeedbackPlace) place, clientFactory);
        }

        return null;
    }
}
