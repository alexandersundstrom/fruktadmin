package com.evry.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class MainContentWidget extends Composite {
    interface MyUiBinder extends UiBinder<Widget, MainContentWidget> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField
    SimplePanel content;

    @UiField
    HeadingElement titleHeader;

    @UiField
    HeadingElement titleText;

    public MainContentWidget() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiChild(tagname = "content")
    public void addContent(Widget widget) {
        content.add(widget);
    }

    public void setHeader(String text) {
        titleHeader.setInnerText(text);
    }

    public void setText(String text) {
        titleText.setInnerText(text);
    }
}
