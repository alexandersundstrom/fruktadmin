package com.evry.client.activites.activity.upload;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class UploadXMLPlace extends Place {
    public UploadXMLPlace() {}

    public static class Tokenizer implements PlaceTokenizer<UploadXMLPlace> {

        @Override
        public UploadXMLPlace getPlace(String token) {
            return new UploadXMLPlace();
        }

        @Override
        public String getToken(UploadXMLPlace place) {
            return "";
        }
    }
}
