package com.evry.client;

import com.evry.client.page.DownloadReportPage;
import com.evry.client.page.HomePage;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class Navigator {

    private static Widget currentPage;

    public static void navigate(Page page) {
        if (currentPage != null) {
            RootPanel.get().remove(currentPage);
        }

        currentPage = getPage(page);

        if(currentPage == null) {
            // Do something
            return;
        }

        RootPanel.get().add(currentPage);
    }

    private static Widget getPage(Page page) {
        switch(page) {
            case HOME_PAGE:
                return new HomePage();
            case DOWNLOAD_REPORT_PAGE:
                return new DownloadReportPage();
        }

        return null;
    }

    public enum Page {
        HOME_PAGE,
        DOWNLOAD_REPORT_PAGE
    }
}
