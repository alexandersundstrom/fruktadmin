package com.evry.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class FeedbackPlace extends Place {
    public FeedbackPlace() {
    }

    public static class Tokenizer implements PlaceTokenizer<FeedbackPlace> {

        @Override
        public FeedbackPlace getPlace(String token) {
            return new FeedbackPlace();
        }

        @Override
        public String getToken(FeedbackPlace place) {
            return "";
        }
    }
}
