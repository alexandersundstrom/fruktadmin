package com.evry.client.activites.activity.upload.widget.form.json;

import com.google.gwt.core.client.JavaScriptObject;

public class XMLUploadDTO extends JavaScriptObject {
    protected XMLUploadDTO() {}

    public final native boolean getSuccess() /*-{ return this.success; }-*/;
    public final native String getMessage() /*-{ return this.message; }-*/;

}
