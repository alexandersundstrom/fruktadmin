package com.evry.client.page;

import com.evry.client.activity.FruktActivity;
import com.evry.client.model.FruktDialogBox;
import com.evry.client.widget.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

public class UploadUpdatePage extends Composite {
    interface MyUiBinder extends UiBinder<Widget, UploadUpdatePage> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField
    MainContentWidget mainContent;

    @UiField
    ListBox uploadOptions;

    private FormWidget currentForm;

    @UiField
    SimplePanel formContent;

    public UploadUpdatePage() {
        initWidget(uiBinder.createAndBindUi(this));

        mainContent.setText("Här kan du ladda upp XML filer för att uppdatera fruktkorgar.");
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
