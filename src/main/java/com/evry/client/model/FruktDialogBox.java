package com.evry.client.model;

import com.evry.client.widget.FruktDialogBoxWidget;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DialogBox;

public class FruktDialogBox extends DialogBox {

    FruktDialogBoxWidget fruktDialogBoxWidget;
    public FruktDialogBox(String title, String description) {

        fruktDialogBoxWidget = new FruktDialogBoxWidget(title, description);
        setWidget(fruktDialogBoxWidget);

        setPopupPosition(400, 100);
    }

    public void setOkClickHandler(ClickHandler clickHandler) {
       fruktDialogBoxWidget.setOkClickHandler(clickHandler, this);
            hide();
    }

    public void setCancelClickHandler(ClickHandler clickHandler) {
        fruktDialogBoxWidget.setCancelClickHandler(clickHandler, this);
        hide();
    }
}
