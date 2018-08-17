package com.evry.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public interface fruktadminServiceAsync {
    void getReports(AsyncCallback<List<ClientReport>> async);
}
