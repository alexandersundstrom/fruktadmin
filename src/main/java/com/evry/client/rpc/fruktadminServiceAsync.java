package com.evry.client.rpc;

import com.evry.client.model.ClientReport;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public interface fruktadminServiceAsync {
    void getReports(AsyncCallback<List<ClientReport>> async);
}
