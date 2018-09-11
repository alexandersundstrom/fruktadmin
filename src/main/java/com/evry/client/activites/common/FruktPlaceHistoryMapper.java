package com.evry.client.activites.common;

import com.evry.client.activites.activity.downloadreport.DownloadReportPlace;
import com.evry.client.activites.activity.feedback.FeedbackPlace;
import com.evry.client.activites.activity.home.HomePlace;
import com.evry.client.activites.activity.upload.UploadXMLPlace;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({DownloadReportPlace.Tokenizer.class, HomePlace.Tokenizer.class, UploadXMLPlace.Tokenizer.class, FeedbackPlace.Tokenizer.class})
public interface FruktPlaceHistoryMapper extends PlaceHistoryMapper {}
