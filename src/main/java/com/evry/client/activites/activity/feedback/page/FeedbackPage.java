package com.evry.client.activites.activity.feedback.page;

import com.evry.client.activites.common.FruktActivity;
import com.evry.client.activites.common.widgets.dialogbox.FruktDialogBox;
import com.evry.client.activites.activity.feedback.widget.form.download.DownloadFeedbackFormWidget;
import com.evry.client.activites.common.FormWidget;
import com.evry.client.activites.common.widgets.maincontent.MainContentWidget;
import com.evry.client.activites.activity.feedback.widget.form.upload.UploadFeedbackFormWidget;
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
    interface MyUiBinder extends UiBinder<Widget, FeedbackPage> {
    }

    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
    @UiField
    MainContentWidget mainContent;

    @UiField
    ListBox feedbackOptions;

    @UiField
    SimplePanel formContent;

    private FormWidget currentForm;

    public FeedbackPage() {
        initWidget(uiBinder.createAndBindUi(this));
        mainContent.setText("Här kan du lämna feedback om du har några synpunkter.");
        currentForm = null;
        feedbackOptions.getElement().getFirstChildElement().setAttribute("disabled", "disabled");
    }

    @UiHandler("feedbackOptions")
    public void onChangeFeedbackOptions(ChangeEvent event) {
        if(currentForm == null) {
            updateForm();
            return;
        }

        if (currentForm.hasUnsubmittedChanges()) {
            FruktDialogBox dialogBox = new FruktDialogBox("Bekräfta", "Du har information i formuläret som inte har skickats, vill du fortsätta?");
            dialogBox.show();

            if (currentForm instanceof DownloadFeedbackFormWidget) {
                dialogBox.setCancelClickHandler(cancelEvent -> feedbackOptions.setItemSelected(1, true));
            } else {
                dialogBox.setCancelClickHandler(cancelEvent -> feedbackOptions.setItemSelected(2, true));
            }

            dialogBox.setOkClickHandler(okEvent -> {
                removeForm();
                updateForm();
            });
        } else {
            removeForm();
            updateForm();
        }
    }

    private void removeForm() {
        if(currentForm != null) {
            formContent.remove((Widget) currentForm);
            currentForm = null;
        }
    }

    public void reset() {
        feedbackOptions.setItemSelected(0, true);
        removeForm();
    }

    public boolean isDirty() {
        return currentForm != null && currentForm.hasUnsubmittedChanges();
    }

    private void updateForm() {
        if (feedbackOptions.getSelectedValue().equals("download")) {
            currentForm = new DownloadFeedbackFormWidget();
            formContent.add((Widget) currentForm);
        } else if (feedbackOptions.getSelectedValue().equals("upload")) {
            currentForm = new UploadFeedbackFormWidget();
            formContent.add((Widget) currentForm);
        }
    }

    public void setFruktActivity(FruktActivity fruktActivity) {
        mainContent.setFruktActivity(fruktActivity);
    }
}
