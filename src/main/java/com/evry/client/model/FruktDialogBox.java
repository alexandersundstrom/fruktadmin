package com.evry.client.model;

import com.evry.client.css.CssResources;
import com.evry.client.widget.FruktDialogBoxWidget;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.*;

public class FruktDialogBox extends DialogBox {
    private Button okButton;
    private Button cancelButton;

    private HandlerRegistration okRegistration;
    private HandlerRegistration cancelRegistration;

    public FruktDialogBox(String title, String description) {
//        CssResources.INSTANCE.dialogBox().ensureInjected();
//
//        VerticalPanel verticalPanel = new VerticalPanel();
//        verticalPanel.addStyleName(CssResources.INSTANCE.dialogBox().fruktdialog());
//        Label titleLabel = new Label(title);
//        titleLabel.addStyleName(CssResources.INSTANCE.dialogBox().fruktdialogTitle());
//        Label descriptionLabel = new Label(description);
//
//        verticalPanel.add(titleLabel);
//        verticalPanel.add(descriptionLabel);
//
//        HorizontalPanel buttonPanel = new HorizontalPanel();
//        buttonPanel.addStyleName(CssResources.INSTANCE.dialogBox().buttonPanel());
//
//        okButton = new Button("OK");
//        cancelButton = new Button("Cancel");
//
//        buttonPanel.add(okButton);
//        buttonPanel.add(cancelButton);
//
//        okRegistration = okButton.addClickHandler(event -> hide());
//        cancelRegistration = cancelButton.addClickHandler(event -> hide());
//
////        verticalPanel.add(okButton);
////        verticalPanel.add(cancelButton);
//        verticalPanel.add(buttonPanel);
//
//        setWidget(verticalPanel);

        setWidget(new FruktDialogBoxWidget());

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
