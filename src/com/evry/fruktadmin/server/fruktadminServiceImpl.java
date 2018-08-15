package com.evry.fruktadmin.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.evry.fruktadmin.client.fruktadminService;

public class fruktadminServiceImpl extends RemoteServiceServlet implements fruktadminService {
    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
}