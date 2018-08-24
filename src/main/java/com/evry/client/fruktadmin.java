package com.evry.client;

import com.evry.client.util.Navigator;
import com.google.gwt.core.client.EntryPoint;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class fruktadmin implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
//        ClickHandlerUtil handler = new ClickHandlerUtil();
//        Anchor downloadreportAnchor = Anchor.wrap(Document.get().getElementById("downloadReport"));
//        downloadreportAnchor.addClickHandler(handler.getReport);
//
//        Anchor downloadreportAnchorNav = Anchor.wrap(Document.get().getElementById("downloadReportNav"));
//        downloadreportAnchorNav.addClickHandler(handler.getReport);
//
//        Anchor uploadReportAnchor = Anchor.wrap(Document.get().getElementById("uploadReport"));
//        uploadReportAnchor.addClickHandler(handler.uploadXML);
//
//        Anchor uploadReportAnchorNav = Anchor.wrap(Document.get().getElementById("uploadReportNav"));
//        uploadReportAnchorNav.addClickHandler(handler.uploadXML);
        Navigator.navigate(Navigator.Page.HOME_PAGE);
    }
}
