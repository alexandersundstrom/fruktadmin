package com.evry.client.rpc;

import com.evry.client.activites.activity.downloadreport.widget.model.ClientReport;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

@RemoteServiceRelativePath("fruktadminService")
public interface FruktadminService extends RemoteService {
    // Sample interface method of remote interface
    List<ClientReport> getReports();

    /**
     * Utility/Convenience class.
     * Use FruktadminService.App.getInstance() to access static instance of fruktadminServiceAsync
     */
    public static class App {
        private static FruktadminServiceAsync ourInstance = GWT.create(FruktadminService.class);

        public static synchronized FruktadminServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
