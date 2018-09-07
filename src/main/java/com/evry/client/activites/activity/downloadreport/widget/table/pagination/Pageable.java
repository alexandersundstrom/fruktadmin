package com.evry.client.activites.activity.downloadreport.widget.table.pagination;

public interface Pageable {
    void goToPage(int page);
    void goToLastPage();
    void goToNextPage();
    void goToPreviousPage();
    void goToFirstPage();

    void setItemsPerPage(int itemsPerPage);
    int getItemsPerPage();

    String getItemName();

    int getFirstPage();
    int getLastPage();
    int getCurrentPage();
    int getItemCount();

    void refresh();
}
