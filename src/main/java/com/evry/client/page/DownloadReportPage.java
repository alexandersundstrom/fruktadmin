package com.evry.client.page;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DownloadReportPage extends Composite {
    interface MyUiBinder extends UiBinder<Widget, DownloadReportPage> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    public DownloadReportPage() {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
