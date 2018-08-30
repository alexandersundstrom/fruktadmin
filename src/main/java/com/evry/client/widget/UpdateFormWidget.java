package com.evry.client.widget;

import com.evry.client.json.XMLUploadResponseMessage;
import com.evry.client.model.FruktDialogBox;
import com.evry.client.util.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Widget;

public class UpdateFormWidget extends Composite implements  FormWidget {
    interface MyUIBinder extends UiBinder<Widget, UpdateFormWidget> { }
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

        XMLUploadResponseMessage result = JsonUtils.safeEval(event.getResults());
       if (result.getSuccess()) {
           FruktDialogBox dialogBox = new FruktDialogBox("Information", result.getMessage(), false);
           dialogBox.show();
       } else {

       }

    }

    @Override
    public boolean hasUnsubmittedChanges() {
        return fileUploader.getFilename() != null && fileUploader.getFilename().length() > 0;
    }
}
