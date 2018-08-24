package com.evry.client.util;

import com.evry.client.page.DownloadReportPage;
import com.evry.client.page.HomePage;
import com.evry.client.page.UploadUpdatePage;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class Navigator {

    private static Widget currentPageWidget;
    private static Page currentPage = null;

    public static void onHashChange(String hash) {
        navigate(Page.fromHash(hash));
    }

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
            case UPLOAD_UPDATE_PAGE:
                return new UploadUpdatePage();
        }

        return null;
    }

    public enum Page {
        HOME_PAGE(""),
        DOWNLOAD_REPORT_PAGE("downloadReport"),
        UPLOAD_UPDATE_PAGE("uploadUpdate");

        private String hash;

        Page(String hash) {
            this.hash = hash;
        }

        public String getHash() {
            return hash;
        }

        public static Page fromHash(String hash) {
            for(Page page : values()) {
                if(page.getHash().equals(hash)) {
                    return page;
                }
            }

            return null;
        }
    }
}
