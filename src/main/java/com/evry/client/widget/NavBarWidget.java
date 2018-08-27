package com.evry.client.widget;

import com.evry.client.css.GrundCss;
import com.evry.client.util.Navigator;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class NavBarWidget extends Composite {
    interface MyUiBinder extends UiBinder<Widget, NavBarWidget>{}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField
    LIElement downloadReport;

    @UiField
    LIElement uploadReport;

    @UiField
    LIElement feedback;

    @UiField
    GrundCss grund;

    public NavBarWidget() {
        initWidget(uiBinder.createAndBindUi(this));

        Navigator.Page currentPage = Navigator.Page.fromHash(Window.Location.getHash().replace("#", ""));

        if(currentPage == null) {
            return;
        }

        switch(currentPage) {
            case DOWNLOAD_REPORT_PAGE:
                downloadReport.addClassName(grund.active());
                break;
            case UPLOAD_UPDATE_PAGE:
                uploadReport.addClassName(grund.active());
                break;
            case FEEDBACK_PAGE:
                feedback.addClassName(grund.active());
                break;
        }
    }
}
