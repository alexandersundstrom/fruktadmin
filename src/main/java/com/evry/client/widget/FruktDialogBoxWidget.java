package com.evry.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
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

    public FruktDialogBoxWidget(String title, String description, boolean showCancel) {
        initWidget(uiBinder.createAndBindUi(this));
        this.title.setInnerText(title);
        this.description.setInnerText(description);
        if (!showCancel) {
            cancelButton.setVisible(false);
        }
    }

    public Button getOkButton() {
        return okButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }
}
