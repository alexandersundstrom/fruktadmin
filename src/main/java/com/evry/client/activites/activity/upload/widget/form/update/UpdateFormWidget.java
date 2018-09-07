package com.evry.client.activites.activity.upload.widget.form.update;

import com.evry.client.activites.activity.FormWidget;
import com.evry.client.activites.activity.upload.widget.form.json.XMLUploadDTO;
import com.evry.client.activites.dependencies.widgets.dialogbox.FruktDialogBox;
import com.evry.client.activites.dependencies.widgets.glass.GlassWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Widget;

public class UpdateFormWidget extends Composite implements FormWidget {
    interface MyUIBinder extends UiBinder<Widget, UpdateFormWidget> {
    }

    private static MyUIBinder uiBinder = GWT.create(MyUIBinder.class);

    @UiField
    GlassWidget glass;

    @UiField
    FileUpload fileUploader;

    public UpdateFormWidget() {
        initWidget(uiBinder.createAndBindUi(this));
        fileUploader.getElement().setAttribute("accept", ".xml");
    }

    @UiHandler("form")
    public void onSubmitForm(FormPanel.SubmitEvent event) {
        glass.on("Laddar up...");
    }

    @UiHandler("form")
    public void onSubmitCompleteFrom(FormPanel.SubmitCompleteEvent event) {
        glass.off();
        XMLUploadDTO result = JsonUtils.safeEval(event.getResults());
        if (result.getSuccess()) {
            fileUploader.getElement().setPropertyString("value", "");
            FruktDialogBox dialogBox = new FruktDialogBox("Information", result.getMessage(), false);
            dialogBox.show();
        } else {
            FruktDialogBox dialogBox = new FruktDialogBox("Fel", result.getMessage(), false);
            dialogBox.show();
        }

    }

    @Override
    public boolean hasUnsubmittedChanges() {
        return fileUploader.getFilename() != null && fileUploader.getFilename().length() > 0;
    }
}
