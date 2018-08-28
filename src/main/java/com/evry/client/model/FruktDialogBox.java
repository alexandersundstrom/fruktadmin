package com.evry.client.model;

import com.evry.client.widget.FruktDialogBoxWidget;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;

public class FruktDialogBox extends DialogBox {
    private FruktDialogBoxWidget fruktDialogBoxWidget;

    private Button okButton;
    private Button cancelButton;

    private HandlerRegistration okButtonRegistration;
    private HandlerRegistration cancelButtonRegistration;

    public FruktDialogBox(String title, String description) {

        fruktDialogBoxWidget = new FruktDialogBoxWidget(title, description);
        setWidget(fruktDialogBoxWidget);

        okButton = fruktDialogBoxWidget.getOkButton();
        cancelButton = fruktDialogBoxWidget.getCancelButton();

        setPopupPosition(400, 100);
    }

    public void setOkClickHandler(ClickHandler clickHandler) {
        if(okButtonRegistration != null) {
            okButtonRegistration.removeHandler();
            okButtonRegistration = null;
        }

        okButtonRegistration = okButton.addClickHandler(event -> {
            clickHandler.onClick(event);
            hide();
        });
    }

    public void setCancelClickHandler(ClickHandler clickHandler) {
        if(cancelButtonRegistration != null) {
            cancelButtonRegistration.removeHandler();
            cancelButtonRegistration = null;
        }

        cancelButtonRegistration = cancelButton.addClickHandler(event -> {
            clickHandler.onClick(event);
            hide();
        });
    }
}
