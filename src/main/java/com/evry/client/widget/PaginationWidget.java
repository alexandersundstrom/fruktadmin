package com.evry.client.widget;

import com.evry.client.css.StylingCss;
import com.evry.client.model.Pageable;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class PaginationWidget extends Composite {
    interface MyUiBinder extends UiBinder<Widget, PaginationWidget> {
    }

    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    private Pageable pageable;

    @UiField
    ListBox itemsPerPageSelector;

    @UiField
    Anchor firstPageButton;

    @UiField
    Anchor previousPageButton;

    @UiField
    Anchor nextPageButton;

    @UiField
    Anchor lastPageButton;

    @UiField
    DivElement pageInfo;

    @UiField
    Element title;

    @UiField
    StylingCss style;

    public PaginationWidget() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public void init(Pageable pageable) {
        this.pageable = pageable;
        title.setInnerText("Antal " + pageable.getItemName() + " per sida");
        updatePageInfo();
    }

    private void updatePageInfo() {
        pageInfo.setInnerText(pageable.getItemName().substring(0, 1).toUpperCase() + pageable.getItemName().substring(1) + " " + (((pageable.getCurrentPage() - 1) * pageable.getItemsPerPage()) + 1) + "-" + (Math.min(pageable.getCurrentPage() * pageable.getItemsPerPage(), pageable.getItemCount()) + " av " + pageable.getItemCount()));
    }

    @UiHandler("itemsPerPageSelector")
    public void onChange(ChangeEvent event) {
        int itemsPerPage = Integer.valueOf(itemsPerPageSelector.getSelectedValue());

        pageable.setItemsPerPage(itemsPerPage == -1 ? pageable.getItemCount() : itemsPerPage);
        if (pageable.getCurrentPage() > pageable.getLastPage()) {
            pageable.goToPage(pageable.getLastPage());
        } else {
            pageable.refresh();
        }

        updatePageInfo();

        if (pageable.getCurrentPage() == pageable.getFirstPage()) {
            firstPageButton.addStyleName(style.disabled());
            previousPageButton.addStyleName(style.disabled());
        } else  {
            firstPageButton.removeStyleName(style.disabled());
            previousPageButton.removeStyleName(style.disabled());
        }

        if (pageable.getCurrentPage() == pageable.getLastPage()) {
            lastPageButton.addStyleName(style.disabled());
            nextPageButton.addStyleName(style.disabled());
        } else  {
            lastPageButton.removeStyleName(style.disabled());
            nextPageButton.removeStyleName(style.disabled());
        }
    }

    @UiHandler("firstPageButton")
    public void onClickFirstPageButton(ClickEvent event) {
        event.preventDefault();
        if (pageable.getCurrentPage() == pageable.getFirstPage()) {
            return;
        }

        if (pageable.getCurrentPage() == pageable.getLastPage()) {
            lastPageButton.removeStyleName(style.disabled());
            nextPageButton.removeStyleName(style.disabled());
        }

        pageable.goToFirstPage();
        updatePageInfo();

        firstPageButton.addStyleName(style.disabled());
        previousPageButton.addStyleName(style.disabled());
    }

    @UiHandler("previousPageButton")
    public void onClickPreviousPageButton(ClickEvent event) {
        event.preventDefault();
        if (pageable.getCurrentPage() == pageable.getFirstPage()) {
            return;
        }

        if (pageable.getCurrentPage() == pageable.getLastPage()) {
            lastPageButton.removeStyleName(style.disabled());
            nextPageButton.removeStyleName(style.disabled());
        }

        pageable.goToPreviousPage();
        updatePageInfo();

        if (pageable.getCurrentPage() == pageable.getFirstPage()) {
            firstPageButton.addStyleName(style.disabled());
            previousPageButton.addStyleName(style.disabled());
        }
    }

    @UiHandler("nextPageButton")
    public void onClickNextPageButton(ClickEvent event) {
        event.preventDefault();
        if (pageable.getCurrentPage() == pageable.getLastPage()) {
            return;
        }

        if (pageable.getCurrentPage() == pageable.getFirstPage()) {
            firstPageButton.removeStyleName(style.disabled());
            previousPageButton.removeStyleName(style.disabled());
        }

        pageable.goToNextPage();
        updatePageInfo();

        if (pageable.getCurrentPage() == pageable.getLastPage()) {
            lastPageButton.addStyleName(style.disabled());
            nextPageButton.addStyleName(style.disabled());
        }
    }

    @UiHandler("lastPageButton")
    public void onClickLastPageButton(ClickEvent event) {
        event.preventDefault();
        if (pageable.getCurrentPage() == pageable.getLastPage()) {
            return;
        }

        if (pageable.getCurrentPage() == pageable.getFirstPage()) {
            firstPageButton.removeStyleName(style.disabled());
            previousPageButton.removeStyleName(style.disabled());
        }

        pageable.goToLastPage();
        updatePageInfo();

        lastPageButton.addStyleName(style.disabled());
        nextPageButton.addStyleName(style.disabled());
    }
}
