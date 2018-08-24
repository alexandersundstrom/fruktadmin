package com.evry.client;

import com.evry.client.util.Navigator;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class fruktadmin implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        History.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                Navigator.onHashChange(event.getValue());
            }
        });

        Navigator.onHashChange(Window.Location.getHash().replace("#", ""));
    }
}
