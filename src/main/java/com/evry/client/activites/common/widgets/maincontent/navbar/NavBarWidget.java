package com.evry.client.activites.common.widgets.maincontent.navbar;

import com.evry.client.activites.common.FruktActivity;
import com.evry.client.activites.common.css.GrundCss;
import com.evry.client.activites.activity.downloadreport.DownloadReportPlace;
import com.evry.client.activites.activity.feedback.FeedbackPlace;
import com.evry.client.activites.activity.upload.UploadXMLPlace;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class NavBarWidget extends Composite {
    interface MyUiBinder extends UiBinder<Widget, NavBarWidget>{}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    private FruktActivity fruktActivity;

    @UiField
    LIElement downloadReportLi;

    @UiField
    LIElement uploadReportLi;

    @UiField
    LIElement feedbackLi;

    @UiField
    Anchor downloadReport;

    @UiField
    Anchor uploadUpdate;

    @UiField
    Anchor feedback;

    @UiField
    GrundCss grund;

    public NavBarWidget() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public void setFruktActivity(FruktActivity fruktActivity) {
        this.fruktActivity = fruktActivity;
    }

    @UiHandler("downloadReport")
    public void onClickDownloadReport(ClickEvent event) {
        fruktActivity.goTo(new DownloadReportPlace("1,10"));
    }

    @UiHandler("uploadUpdate")
    public void onClickUploadUpdate(ClickEvent event) {
        fruktActivity.goTo(new UploadXMLPlace());
    }

    @UiHandler("feedback")
    public void onClickFeedback(ClickEvent event) {
        fruktActivity.goTo(new FeedbackPlace());
    }
}
