package com.evry.client.widget;

import com.evry.client.util.Navigator;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import java.util.logging.Logger;

public class NavBarWidget extends Composite {
    interface MyUiBinder extends UiBinder<Widget, NavBarWidget>{}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
    private static Logger logger = Logger.getLogger("Things");

    @UiField
    Anchor downloadReportNav;

    public NavBarWidget() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("downloadReportNav")
    void handleClick(ClickEvent event) {
        Navigator.navigate(Navigator.Page.DOWNLOAD_REPORT_PAGE);
    }
}
