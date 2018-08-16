package com.evry.fruktadmin.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class fruktadmin implements EntryPoint {
    DivElement contentDiv = DivElement.as(Document.get().getElementById("spar-content"));

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        DivElement contentDiv = DivElement.as(Document.get().getElementById("spar-content"));
        Anchor downloadreportAnchor = Anchor.wrap(Document.get().getElementById("getReport"));
        Anchor downloadreportAnchorNav = Anchor.wrap(Document.get().getElementById("getReportNav"));
        Anchor uploadReportAnchor = Anchor.wrap(Document.get().getElementById("uploadReport"));
        Anchor uploadReportAnchorNav = Anchor.wrap(Document.get().getElementById("uploadReportNav"));

        downloadreportAnchor.addClickHandler(getReport);
        downloadreportAnchorNav.addClickHandler(getReport);

        uploadReportAnchor.addClickHandler(uploadXML);
        uploadReportAnchorNav.addClickHandler(uploadXML);


//        RootPanel.get("slot1").add(button);
//        RootPanel.get("slot2").add(label);
    }

//    private static class MyAsyncCallback implements AsyncCallback<String> {
//        private Label label;
//
//        public MyAsyncCallback(Label label) {
//            this.label = label;
//        }
//
//        public void onSuccess(String result) {
//            label.getElement().setInnerHTML(result);
//        }
//
//        public void onFailure(Throwable throwable) {
//            label.setText("Failed to receive answer from server!");
//        }
//    }

    private ClickHandler getReport = event -> {
        contentDiv.setInnerHTML(Resources.INSTANCE.getReport().getText());
    };

    private ClickHandler uploadXML = event -> {
        contentDiv.setInnerHTML(Resources.INSTANCE.uploadXML().getText());
    };
}
