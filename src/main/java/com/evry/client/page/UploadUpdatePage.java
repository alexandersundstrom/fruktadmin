package com.evry.client.page;

import com.evry.client.widget.GlassWidget;
import com.evry.client.widget.MainContentWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Widget;

public class UploadUpdatePage extends Composite {
    interface MyUiBinder extends UiBinder<Widget, UploadUpdatePage> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField
    MainContentWidget mainContent;

    @UiField
    FormPanel form;

    @UiField
    FileUpload fileUploader;

    @UiField
    GlassWidget glass;

    public UploadUpdatePage() {
        initWidget(uiBinder.createAndBindUi(this));

        mainContent.setText("Här kan du ladda upp XML filer för att uppdatera fruktkorgar.");
        fileUploader.getElement().setAttribute("accept", ".xml");
    }

    @UiHandler("form")
    public void onSubmitForm(FormPanel.SubmitEvent event) {
        glass.on("Laddar up...");
    }

    @UiHandler("form")
    public void onSubmitCompleteFrom(FormPanel.SubmitCompleteEvent event) {
        glass.off();

        Window.alert(event.getResults());
    }
}
