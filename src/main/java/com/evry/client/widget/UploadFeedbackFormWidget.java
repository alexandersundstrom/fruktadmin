package com.evry.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class UploadFeedbackFormWidget extends Composite {
    interface MyUiBinder extends UiBinder<Widget, UploadFeedbackFormWidget> {}
    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    public UploadFeedbackFormWidget() {
        initWidget(uiBinder.createAndBindUi(this));
    }
}