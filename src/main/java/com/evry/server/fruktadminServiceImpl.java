package com.evry.server;

import com.evry.client.fruktadminService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class fruktadminServiceImpl extends RemoteServiceServlet implements fruktadminService {
    // Implementation of sample interface method
    public String getMessage(String msg)
    {

        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
}