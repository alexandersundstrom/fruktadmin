package com.evry.client.css;

import com.google.gwt.resources.client.CssResource;

public interface DialogBoxCss extends CssResource {
    String fruktdialog();

    @ClassName("fruktdialog-title")
    String fruktdialogTitle();

    String buttonContainer();

    @ClassName("cancel-button")
    String cancelButton();

    @ClassName("fruktdialog-description")
    String fruktdialogDescription();
}
