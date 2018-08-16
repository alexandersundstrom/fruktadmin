package com.evry.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("fruktadminService")
public interface fruktadminService extends RemoteService {
    // Sample interface method of remote interface
    String getMessage(String msg);

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
