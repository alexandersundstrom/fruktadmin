package com.evry.client.place;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({DownloadReportPlace.Tokenizer.class, HomePlace.Tokenizer.class, UploadUpdatePlace.Tokenizer.class, FeedbackPlace.Tokenizer.class})
public interface FruktPlaceHistoryMapper extends PlaceHistoryMapper {}
