package com.evry.client.model;

import com.evry.client.css.CssResources;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class FruktDialogBox extends DialogBox {
    private Button okButton;
    private Button cancelButton;

    private HandlerRegistration okRegistration;
    private HandlerRegistration cancelRegistration;

    public FruktDialogBox(String title, String description) {
        CssResources.INSTANCE.dialogBox().ensureInjected();

        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.addStyleName(CssResources.INSTANCE.dialogBox().fruktdialog());
        Label titleLabel = new Label(title);
        titleLabel.addStyleName(CssResources.INSTANCE.dialogBox().fruktdialogTitle());
        Label descriptionLabel = new Label(description);

        verticalPanel.add(titleLabel);
        verticalPanel.add(descriptionLabel);

        okButton = new Button("OK");
        cancelButton = new Button("Cancel");

        okRegistration = okButton.addClickHandler(event -> hide());
        cancelRegistration = cancelButton.addClickHandler(event -> hide());

        verticalPanel.add(okButton);
        verticalPanel.add(cancelButton);

        setWidget(verticalPanel);

        setPopupPosition(400, 100);
    }

    public void setOkClickHandler(ClickHandler clickHandler) {
        okRegistration.removeHandler();
        okRegistration = okButton.addClickHandler(event -> {
            clickHandler.onClick(event);
            hide();
        });
    }

    public void setCancelClickHandler(ClickHandler clickHandler) {
        cancelRegistration.removeHandler();
        cancelRegistration = cancelButton.addClickHandler(event -> {
            clickHandler.onClick(event);
            hide();
        });
    }
}
