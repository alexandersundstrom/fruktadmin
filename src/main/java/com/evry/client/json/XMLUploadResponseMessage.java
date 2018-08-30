package com.evry.client.json;

import com.google.gwt.core.client.JavaScriptObject;

public class XMLUploadResponseMessage extends JavaScriptObject {
    protected XMLUploadResponseMessage() {}

    public final native boolean getSuccess() /*-{ return this.success; }-*/;
    public final native String getMessage() /*-{ return this.message; }-*/;

}
