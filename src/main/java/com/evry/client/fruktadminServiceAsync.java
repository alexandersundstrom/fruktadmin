package com.evry.client;

import com.evry.fruktkorgservice.model.ImmutableReport;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public interface fruktadminServiceAsync {
    void getReports(AsyncCallback<List<ImmutableReport>> async);
}
