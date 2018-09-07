package com.evry.client.activites.dependencies.widgets.glass;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class GlassWidget extends Composite {
    interface MyUiBinder extends UiBinder<Widget, GlassWidget> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    private int concurrentGlassOnCount;

    @UiField
    DivElement background;

    @UiField
    DivElement text;

    public GlassWidget() {
        concurrentGlassOnCount = 0;
        initWidget(uiBinder.createAndBindUi(this));
    }

    public void on(String message) {
        concurrentGlassOnCount++;
        text.setInnerHTML(message);
        background.getStyle().setVisibility(Style.Visibility.VISIBLE);
        text.getStyle().setVisibility(Style.Visibility.VISIBLE);
    }

    public void off() {
        if (--concurrentGlassOnCount == 0) {
            background.getStyle().setVisibility(Style.Visibility.HIDDEN);
            text.getStyle().setVisibility(Style.Visibility.HIDDEN);
        }
    }
}
