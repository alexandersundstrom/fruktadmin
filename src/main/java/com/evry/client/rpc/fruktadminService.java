package com.evry.client.rpc;

import com.evry.client.model.ClientReport;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

@RemoteServiceRelativePath("fruktadminService")
public interface fruktadminService extends RemoteService {
    // Sample interface method of remote interface
    List<ClientReport> getReports();

    /**
     * Utility/Convenience class.
     * Use fruktadminService.App.getInstance() to access static instance of fruktadminServiceAsync
     */
    public static class App {
        private static fruktadminServiceAsync ourInstance = GWT.create(fruktadminService.class);

        public static synchronized fruktadminServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
