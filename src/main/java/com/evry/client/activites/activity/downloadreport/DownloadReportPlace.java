package com.evry.client.activites.activity.downloadreport;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class DownloadReportPlace extends Place {
    private int page;
    private int reportsPerPage;

    public DownloadReportPlace() {
        this("1,10");
    }

    public DownloadReportPlace(String token) {
        String[] split = token.split(",");
        page = Integer.parseInt(split[0]);
        reportsPerPage = Integer.parseInt(split[1]);
    }

    public int getPage() {
        return page;
    }

    public int getReportsPerPage() {
        return reportsPerPage;
    }

    public static class Tokenizer implements PlaceTokenizer<DownloadReportPlace> {

        @Override
        public DownloadReportPlace getPlace(String token) {
            return new DownloadReportPlace(token);
        }

        @Override
        public String getToken(DownloadReportPlace place) {
            return place.page + "," + place.reportsPerPage;
        }
    }
}
