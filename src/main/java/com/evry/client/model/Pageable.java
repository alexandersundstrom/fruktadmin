package com.evry.client.model;

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
