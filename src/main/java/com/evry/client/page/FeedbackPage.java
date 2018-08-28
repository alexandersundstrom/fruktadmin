package com.evry.client.page;

import com.evry.client.activity.FruktActivity;
import com.evry.client.widget.DownloadFeedbackFormWidget;
import com.evry.client.widget.MainContentWidget;
import com.evry.client.widget.UploadFeedbackFormWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class FeedbackPage extends Composite {
    interface MyUiBinder extends UiBinder<Widget, FeedbackPage> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
    @UiField
    MainContentWidget mainContent;

    @UiField
    ListBox feedbackOptions;

    @UiField
    SimplePanel formContent;

    private Widget currentForm;

    public FeedbackPage() {
        initWidget(uiBinder.createAndBindUi(this));
        mainContent.setText("Här kan du lämna feedback om du har några synpunkter.");
        currentForm = null;
        feedbackOptions.getElement().getFirstChildElement().setAttribute("disabled", "disabled");
    }

    @UiHandler("feedbackOptions")
    public void onChangeFeedbackOptions(ChangeEvent event) {
        if(currentForm != null) {
            formContent.remove(currentForm);
            currentForm = null;
        }

        if(feedbackOptions.getSelectedValue().equals("download")) {
            currentForm = new DownloadFeedbackFormWidget();
            formContent.add(currentForm);
        } else if(feedbackOptions.getSelectedValue().equals("upload")) {
            currentForm = new UploadFeedbackFormWidget();
            formContent.add(currentForm);
        }
    }

    public void setFruktActivity(FruktActivity fruktActivity) {
        mainContent.setFruktActivity(fruktActivity);
    }
}
