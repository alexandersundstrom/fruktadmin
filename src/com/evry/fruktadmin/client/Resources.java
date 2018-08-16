package com.evry.fruktadmin.client;


import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public interface Resources extends ClientBundle{
    Resources INSTANCE = GWT.create(Resources.class);

    @Source("downloadReport.html")
    TextResource getReport();

    @Source("upploadXML.html")
    TextResource uploadXML();
}
