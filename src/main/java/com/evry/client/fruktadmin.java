package com.evry.client;

import com.evry.client.activity.FruktActivityMapper;
import com.evry.client.core.ClientFactory;
import com.evry.client.place.FruktPlaceHistoryMapper;
import com.evry.client.place.HomePlace;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class fruktadmin implements EntryPoint {
    private Place defaultPlace = new HomePlace();
    private SimplePanel appWidget = new SimplePanel();
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        ClientFactory clientFactory = GWT.create(ClientFactory.class);
        EventBus eventBus = clientFactory.getEventBus();
        PlaceController placeController = clientFactory.getPlaceController();

        ActivityMapper activityMapper = new FruktActivityMapper(clientFactory);
        ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
        activityManager.setDisplay(appWidget);

        FruktPlaceHistoryMapper historyMapper = GWT.create(FruktPlaceHistoryMapper.class);
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, eventBus, defaultPlace);

        RootPanel.get().add(appWidget);

        historyHandler.handleCurrentHistory();
    }
}
