package com.evry.client.activites.dependencies.css;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface CssResources extends ClientBundle {
    public static final CssResources INSTANCE = GWT.create(CssResources.class);

    @Source("dialogBox.css")
    DialogBoxCss dialogBox();
}
