package com.evry.client.activites.activity.upload.page;

import com.evry.client.activites.common.FormWidget;
import com.evry.client.activites.common.FruktActivity;
import com.evry.client.activites.activity.upload.widget.form.restore.RestoreFormWidget;
import com.evry.client.activites.activity.upload.widget.form.update.UpdateFormWidget;
import com.evry.client.activites.common.widgets.dialogbox.FruktDialogBox;
import com.evry.client.activites.common.widgets.maincontent.MainContentWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;

public class UploadXMLPage extends Composite {
    interface MyUiBinder extends UiBinder<Widget, UploadXMLPage> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField
    MainContentWidget mainContent;

    @UiField
    ListBox uploadOptions;

    private FormWidget currentForm;

    @UiField
    SimplePanel formContent;

    public UploadXMLPage() {
        initWidget(uiBinder.createAndBindUi(this));

        mainContent.setText("Här kan du ladda upp XML filer för att uppdatera eller återställa dina fruktkorgar.");
        uploadOptions.getElement().getFirstChildElement().setAttribute("disabled", "disabled");
        currentForm = null;
    }

    @UiHandler("uploadOptions")
    public void onChangeFeedbackOptions(ChangeEvent event) {
        if(currentForm == null) {
            updateForm();
            return;
        }

        if (currentForm.hasUnsubmittedChanges()) {
            FruktDialogBox dialogBox = new FruktDialogBox("Bekräfta", "Filen har inte skickats, vill du fortsätta?");
            dialogBox.show();

            if (currentForm instanceof UpdateFormWidget) {
                dialogBox.setCancelClickHandler(cancelEvent -> uploadOptions.setItemSelected(1, true));
            } else {
                dialogBox.setCancelClickHandler(cancelEvent -> uploadOptions.setItemSelected(2, true));
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

    private void updateForm() {
        if (uploadOptions.getSelectedValue().equals("update")) {
            currentForm = new UpdateFormWidget();
            formContent.add((Widget) currentForm);
        } else if (uploadOptions.getSelectedValue().equals("restore")) {
            currentForm = new RestoreFormWidget();
            formContent.add((Widget) currentForm);
        }
    }

    private void removeForm() {
        if(currentForm != null) {
            formContent.remove((Widget) currentForm);
            currentForm = null;
        }
    }

    public void setFruktActivity(FruktActivity fruktActivity) {
        mainContent.setFruktActivity(fruktActivity);
    }
}
