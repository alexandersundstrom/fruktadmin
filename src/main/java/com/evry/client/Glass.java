package com.evry.client;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;

public class Glass {
    private static int concurrentGlassOnCount = 0;
    private static DivElement loadingBackground = DivElement.as(Document.get().getElementById("laddar_bakgrund"));
    private static DivElement loadingText = DivElement.as(Document.get().getElementById("laddar_text"));

    public static void on(String text) {
        concurrentGlassOnCount++;
        loadingText.setInnerHTML(text);
        loadingBackground.getStyle().setVisibility(Style.Visibility.VISIBLE);
        loadingText.getStyle().setVisibility(Style.Visibility.VISIBLE);
    }

    public static void off() {
        if (--concurrentGlassOnCount == 0) {
            loadingBackground.getStyle().setVisibility(Style.Visibility.HIDDEN);
            loadingText.getStyle().setVisibility(Style.Visibility.HIDDEN);
        }
    }
}
