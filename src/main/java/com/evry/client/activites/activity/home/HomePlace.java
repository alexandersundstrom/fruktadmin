package com.evry.client.activites.activity.home;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class HomePlace extends Place {
    public HomePlace() {}

    public static class Tokenizer implements PlaceTokenizer<HomePlace> {

        @Override
        public HomePlace getPlace(String token) {
            return new HomePlace();
        }

        @Override
        public String getToken(HomePlace place) {
            return "";
        }
    }
}
