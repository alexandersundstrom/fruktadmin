package com.evry.client.util;

import com.evry.client.page.DownloadReportPage;
import com.evry.client.page.HomePage;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class Navigator {

    private static Widget currentPageWidget;
    private static Page currentPage = null;

    public static void navigate(Page page) {
        if(currentPage == page) {
            return;
        }

        if(currentPageWidget != null) {
            RootPanel.get().remove(currentPageWidget);
        }

        currentPageWidget = getPage(page);

        if(currentPageWidget == null) {
            // Do something
            return;
        }

        currentPage = page;

        RootPanel.get().add(currentPageWidget);
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
