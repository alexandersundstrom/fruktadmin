package com.evry.client.widget;

import com.evry.client.model.FruktDialogBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;

public class DownloadFeedbackFormWidget extends Composite {
    interface MyUiBinder extends UiBinder<Widget, DownloadFeedbackFormWidget> {
    }

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField
    TextBox email;

    @UiField
    FormPanel form;

    @UiField
    RadioButton rating1;

    @UiField
    RadioButton rating2;

    @UiField
    RadioButton rating3;

    @UiField
    RadioButton rating4;

    @UiField
    CheckBox termAndConditions;

    public DownloadFeedbackFormWidget() {
        initWidget(uiBinder.createAndBindUi(this));

        email.getElement().setAttribute("type", "email");
        email.getElement().setAttribute("placeholder", "Ange email");
    }

    @UiHandler("form")
    public void onSubmitForm(FormPanel.SubmitEvent event) {
        String emailString = email.getValue().trim();

        if (!validateEmail(emailString)) {
            FruktDialogBox dialogBox = new FruktDialogBox("Validering", "Du måste ange en korrekt email adress", false);
            dialogBox.show();
            event.cancel();
            return;
        }

        if(!validateRadioButtons()) {
            FruktDialogBox dialogBox = new FruktDialogBox("Validering", "Du måste välja en ranking", false);
            dialogBox.show();
            event.cancel();
            return;
        }

        if(!validateTermsAndConditions()) {
            FruktDialogBox dialogBox = new FruktDialogBox("Validering", "Du måste acceptera villkoren", false);
            dialogBox.show();

            event.cancel();
            return;
        }
        FruktDialogBox dialogBox = new FruktDialogBox("Formulär skickat", "Tack för din feedback!", false);
        dialogBox.show();
    }

    private boolean validateEmail(String emailString) {
        if (emailString == null || emailString.length() == 0) {
            return false;
        }

        // Checks the email string agains an email regular expression
        return RegExp.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])").test(emailString);
    }

    private boolean validateRadioButtons() {
        return rating1.getValue() || rating2.getValue() || rating3.getValue() || rating4.getValue();
    }

    private boolean validateTermsAndConditions() {
        return termAndConditions.getValue();
    }
}
