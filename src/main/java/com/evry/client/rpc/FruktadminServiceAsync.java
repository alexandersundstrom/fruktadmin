package com.evry.client.rpc;

import com.evry.client.activites.activity.downloadreport.widget.model.ClientReport;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public interface FruktadminServiceAsync {
    void getReports(AsyncCallback<List<ClientReport>> async);
}
