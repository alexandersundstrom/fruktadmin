package com.evry.client.page;

import com.evry.client.widget.MainContentWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class FeedbackPage extends Composite {
    interface MyUiBinder extends UiBinder<Widget, FeedbackPage> {}
    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
    @UiField
    MainContentWidget mainContent;

    @UiField
    ListBox feedBackOptions;

    public FeedbackPage() {
        initWidget(uiBinder.createAndBindUi(this));
        mainContent.setText("Här kan du lämna feedback om du har några synpunkter.");
    }
}
