package com.evry.client.widget;

import com.evry.client.model.FruktDialogBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class FruktDialogBoxWidget extends Composite {
    interface MyUiBinder extends UiBinder<Widget, FruktDialogBoxWidget> {
    }

    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField
    SpanElement title;
    @UiField
    SpanElement description;
    @UiField
    Button okButton;
    @UiField
    Button cancelButton;

    private HandlerRegistration okRegistration;
    private HandlerRegistration cancelRegistration;

    public FruktDialogBoxWidget(String title, String description) {
        initWidget(uiBinder.createAndBindUi(this));
        this.title.setInnerText(title);
        this.description.setInnerText(description);
    }

    public void setOkClickHandler(ClickHandler clickHandler, FruktDialogBox dialogBox) {
        okRegistration = okButton.addClickHandler(event -> {
            clickHandler.onClick(event);
            dialogBox.hide();
        });
    }

    public void setCancelClickHandler(ClickHandler clickHandler, FruktDialogBox dialogBox) {
        cancelRegistration = cancelButton.addClickHandler(event -> {
            clickHandler.onClick(event);
            dialogBox.hide();
        });
    }

}
