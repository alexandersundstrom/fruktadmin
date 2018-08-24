package com.evry.client.page;

import com.evry.client.widget.MainContentWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class HomePage extends Composite {
    interface MyUiBinder extends UiBinder<Widget, HomePage> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField
    MainContentWidget mainContent;

    public HomePage() {
        initWidget(uiBinder.createAndBindUi(this));

        mainContent.setText("Här kan du ladda ner en sammanställning av alla fruktkorgar, eller uppdatera fruktkorgarna.");
    }
}
