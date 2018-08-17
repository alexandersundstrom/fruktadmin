package com.evry.client;

import com.evry.client.Utils.ClickHandlerUtil;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Anchor;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class fruktadmin implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        Anchor downloadreportAnchor = Anchor.wrap(Document.get().getElementById("downloadReport"));
        downloadreportAnchor.addClickHandler(ClickHandlerUtil.getReport);

        Anchor downloadreportAnchorNav = Anchor.wrap(Document.get().getElementById("downloadReportNav"));
        downloadreportAnchorNav.addClickHandler(ClickHandlerUtil.getReport);

        Anchor uploadReportAnchor = Anchor.wrap(Document.get().getElementById("uploadReport"));
        uploadReportAnchor.addClickHandler(ClickHandlerUtil.uploadXML);

        Anchor uploadReportAnchorNav = Anchor.wrap(Document.get().getElementById("uploadReportNav"));
        uploadReportAnchorNav.addClickHandler(ClickHandlerUtil.uploadXML);
    }
}
