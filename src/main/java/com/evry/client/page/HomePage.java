package com.evry.client.page;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class HomePage extends Composite {
    interface MyUiBinder extends UiBinder<Widget, HomePage> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    public HomePage() {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
