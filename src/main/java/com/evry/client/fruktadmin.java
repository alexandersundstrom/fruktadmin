package com.evry.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class fruktadmin implements EntryPoint {
    private static final String DOWNLOAD = "download";
    private static final String UPLOAD = "uppload";

    private Glass glass = new Glass();
    private DivElement contentDiv = DivElement.as(Document.get().getElementById("spar-content"));
    private LIElement downloadLi = LIElement.as(Document.get().getElementById("menu_load"));
    private LIElement uploadLi = LIElement.as(Document.get().getElementById("menu_upload"));

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        Anchor downloadreportAnchor = Anchor.wrap(Document.get().getElementById("downloadReport"));
        downloadreportAnchor.addClickHandler(getReport);

        Anchor downloadreportAnchorNav = Anchor.wrap(Document.get().getElementById("downloadReportNav"));
        downloadreportAnchorNav.addClickHandler(getReport);

        Anchor uploadReportAnchor = Anchor.wrap(Document.get().getElementById("uploadReport"));
        uploadReportAnchor.addClickHandler(uploadXML);

        Anchor uploadReportAnchorNav = Anchor.wrap(Document.get().getElementById("uploadReportNav"));
        uploadReportAnchorNav.addClickHandler(uploadXML);

        fruktadminServiceAsync messageThing = GWT.create(fruktadminService.class);
        messageThing.getMessage("Forkster", new MyAsyncCallback(contentDiv));

//        RootPanel.get("slot1").add(button);
//        RootPanel.get("slot2").add(label);
    }

    private static class MyAsyncCallback implements AsyncCallback<String> {
        private DivElement div;

        MyAsyncCallback(DivElement div) {
            this.div = div;
        }

        public void onSuccess(String result) {
            div.setInnerHTML(result);
        }

        public void onFailure(Throwable throwable) {
        }
    }

    private ClickHandler getReport = event -> {
        contentDiv.setInnerHTML(Resources.INSTANCE.getReport().getText());
        switchContent(DOWNLOAD);
    };

    private ClickHandler uploadXML = event -> {
        contentDiv.setInnerHTML(Resources.INSTANCE.uploadXML().getText());
        switchContent(UPLOAD);
    };

    private void switchContent(String page) {
        switch (page) {
            case DOWNLOAD:
                RootPanel.get("menu_load").addStyleName("active");
                RootPanel.get("menu_upload").removeStyleName("active");
                break;
            case UPLOAD:
                RootPanel.get("menu_upload").addStyleName("active");
                RootPanel.get("menu_load").removeStyleName("active");
                break;

        }
    }
}
