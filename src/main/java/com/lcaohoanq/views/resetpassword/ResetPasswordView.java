package com.lcaohoanq.views.resetpassword;

import com.lcaohoanq.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Reset Password")
@Route(value = "reset-password", layout = MainLayout.class)
public class ResetPasswordView extends Composite<VerticalLayout> {

    private H3 title = new H3();
    private TextField textField_New_Password = new TextField("Enter New Password");
    private TextField textField_Confirmed_Password = new TextField("Confirmed Password");
    private Button button_Update = new Button("Send");
    private VerticalLayout layoutRow = new VerticalLayout();
    private VerticalLayout layoutColumn2 = new VerticalLayout();

    public ResetPasswordView() {
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.START);
        getContent().setAlignItems(Alignment.CENTER);
        initComponent();
        doAction();
    }

    private void initComponent(){
        title.setText("Reset Password");
        title.setWidth("max-content");

        textField_New_Password.setWidth("50%");
        button_Update.setWidth("min-content");

        textField_Confirmed_Password.setWidth("50%");

        layoutColumn2.add(title, textField_New_Password, textField_Confirmed_Password, button_Update);
        layoutColumn2.setAlignItems(Alignment.CENTER);
        layoutColumn2.setJustifyContentMode(JustifyContentMode.START);
        layoutColumn2.setPadding(true);
        layoutColumn2.setSpacing(true);

        layoutRow.add(layoutColumn2);
        layoutRow.setAlignItems(Alignment.CENTER);
        layoutRow.setJustifyContentMode(JustifyContentMode.START);
        layoutRow.setPadding(true);
        layoutRow.setSpacing(true);

        getContent().add(layoutRow);
    }

    private void doAction(){
        // Initially disable the button
        button_Update.setEnabled(false);

        // Add value change listeners to the text fields
        textField_New_Password.addValueChangeListener(event -> validatePasswords());
        textField_Confirmed_Password.addValueChangeListener(event -> validatePasswords());

        // Add click listener to the button
        button_Update.addClickListener(event -> {
            if (textField_New_Password.getValue().equals(textField_Confirmed_Password.getValue())) {
                // Handle the API call to reset the password
                Notification.show("Password reset successfully");
            } else {
                Notification.show("Passwords do not match");
            }
        });
    }

    private void validatePasswords() {
        String newPassword = textField_New_Password.getValue();
        String confirmedPassword = textField_Confirmed_Password.getValue();

        // Password validation rules
        if (newPassword.length() < 8) {
            textField_New_Password.setErrorMessage("Password must be at least 8 characters long");
            textField_New_Password.setInvalid(true);
        } else if (!newPassword.matches(".*[A-Z].*")) {
            textField_New_Password.setErrorMessage("Password must contain at least one uppercase letter");
            textField_New_Password.setInvalid(true);
        } else if (!newPassword.matches(".*[a-z].*")) {
            textField_New_Password.setErrorMessage("Password must contain at least one lowercase letter");
            textField_New_Password.setInvalid(true);
        } else if (!newPassword.matches(".*\\d.*")) {
            textField_New_Password.setErrorMessage("Password must contain at least one number");
            textField_New_Password.setInvalid(true);
        } else {
            textField_New_Password.setInvalid(false);
            textField_New_Password.setErrorMessage(null);
        }

        if (!newPassword.isEmpty() && !confirmedPassword.isEmpty() && newPassword.equals(confirmedPassword)) {
            button_Update.setEnabled(true);
        } else {
            textField_Confirmed_Password.setErrorMessage("Password must be matching");
            textField_Confirmed_Password.setInvalid(true);
            button_Update.setEnabled(false);
        }
    }

}
