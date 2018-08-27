package com.evry.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class FruktDialogBoxWidget extends Composite {
    interface MyUiBinder extends UiBinder<Widget, FruktDialogBoxWidget> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    public FruktDialogBoxWidget() {
        initWidget(uiBinder.createAndBindUi(this));
    }

}
