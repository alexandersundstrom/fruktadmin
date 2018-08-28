package com.evry.client.page;

import com.evry.client.activity.FruktActivity;
import com.evry.client.place.DownloadReportPlace;
import com.evry.client.place.FeedbackPlace;
import com.evry.client.place.UploadUpdatePlace;
import com.evry.client.widget.MainContentWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class HomePage extends Composite {
    interface MyUiBinder extends UiBinder<Widget, HomePage> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    private FruktActivity fruktActivity;

    @UiField
    MainContentWidget mainContent;

    @UiField
    Anchor downloadReport;

    @UiField
    Anchor uploadReport;

    @UiField
    Anchor feedback;

    public HomePage() {
        initWidget(uiBinder.createAndBindUi(this));

        mainContent.setText("Här kan du ladda ner en sammanställning av alla fruktkorgar, eller uppdatera fruktkorgarna.");
    }

    public void setFruktActivity(FruktActivity fruktActivity) {
        this.fruktActivity = fruktActivity;
        mainContent.setFruktActivity(fruktActivity);
    }

    @UiHandler("downloadReport")
    public void onClickDownloadReport(ClickEvent event) {
        fruktActivity.goTo(new DownloadReportPlace("1,10"));
    }

    @UiHandler("uploadReport")
    public void onClickUploadReport(ClickEvent event) {
        fruktActivity.goTo(new UploadUpdatePlace());
    }

    @UiHandler("feedback")
    public void onClickFeedback(ClickEvent event) {
        fruktActivity.goTo(new FeedbackPlace());
    }
}
