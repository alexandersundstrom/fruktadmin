package com.evry.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface fruktadminServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);
}
