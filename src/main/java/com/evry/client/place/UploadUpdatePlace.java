package com.evry.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class UploadUpdatePlace extends Place {
    public UploadUpdatePlace() {}

    public static class Tokenizer implements PlaceTokenizer<UploadUpdatePlace> {

        @Override
        public UploadUpdatePlace getPlace(String token) {
            return new UploadUpdatePlace();
        }

        @Override
        public String getToken(UploadUpdatePlace place) {
            return "";
        }
    }
}
