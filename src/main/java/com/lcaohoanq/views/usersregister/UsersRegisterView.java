package com.lcaohoanq.views.usersregister;

import com.lcaohoanq.utils.ApiUtils;
import com.lcaohoanq.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@PageTitle("Snake Game")
@Getter
@Setter
@Route(value = "users/register", layout = MainLayout.class)
public class UsersRegisterView extends Composite<VerticalLayout> {

    private H3 title = new H3();
    private TextField textField_Email_Phone = new TextField("Email or Phone Number");
    private TextField textField_First_Name = new TextField("First Name");
    private TextField textField_Last_Name = new TextField("Last Name");
    private TextField textField_Password = new TextField("Password");
    private TextField textField_Confirmed_Password = new TextField("Confirmed password");
    private FormLayout formLayout2Col = new FormLayout();
    private HorizontalLayout layoutRow = new HorizontalLayout();
    private VerticalLayout layoutColumn2 = new VerticalLayout();
    private Button button_Save = new Button("Save");
    private Button button_Cancel = new Button("Cancel");

    public UsersRegisterView() {
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.START);
        getContent().setAlignItems(Alignment.CENTER);
        initComponent();
        doAction();
    }

    private void initComponent(){
        title.setText("Register");
        title.setWidth("min-content");

        textField_Email_Phone.setWidth("100%");
        textField_First_Name.setWidth("min-content");
        textField_Last_Name.setWidth("min-content");
        textField_Password.setWidth("min-content");
        textField_Confirmed_Password.setWidth("min-content");

        layoutRow.setWidthFull();
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");

        layoutColumn2.setWidthFull();
        layoutColumn2.setWidth("100%");
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");
        layoutColumn2.setFlexGrow(1.0, layoutRow);

        button_Save.setWidth("min-content");
        button_Save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        button_Cancel.setWidth("min-content");

        getContent().setFlexGrow(1.0, layoutColumn2);

        formLayout2Col.setWidth("100%");

        getContent().add(layoutColumn2);
        layoutColumn2.add(title);
        layoutColumn2.add(textField_Email_Phone);
        layoutColumn2.add(formLayout2Col);
        formLayout2Col.add(textField_First_Name);
        formLayout2Col.add(textField_Last_Name);
        formLayout2Col.add(textField_Password);
        formLayout2Col.add(textField_Confirmed_Password);
        layoutColumn2.add(layoutRow);
        layoutRow.add(button_Save);
        layoutRow.add(button_Cancel);
    }

    private void doAction(){
        button_Save.addClickListener(event -> {
            String email_phone = textField_Email_Phone.getValue();
            String first_name = textField_First_Name.getValue();
            String last_name = textField_Last_Name.getValue();
            String password = textField_Password.getValue();
            String confirmed_password = textField_Confirmed_Password.getValue();

            List<String> dataList = List.of(
                email_phone, first_name, last_name, password, confirmed_password);

            boolean isEmpty = dataList.stream().anyMatch(String::isEmpty);

            if(isEmpty) {
                System.out.println("All fields must be filled.");
            } else {
                try{
                    Map<String, Object> payload = Map.of(
                        "id", -1,
                        "firstName", first_name
                            , "lastName", last_name,
                        "email", email_phone,
                        "phone", email_phone,
                        "password", password);
                    HttpResponse<String> response = ApiUtils.postRequest("http://localhost:8081/users/register", payload);
                }catch (Exception e){
                    System.out.println("An error occurred: " + e.getMessage());
                }
            }

        });
    }

    record SampleItem(String value, String label, Boolean disabled) {
    }

    private void setSelectSampleData(Select select) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("state1", "State 1", null));
        sampleItems.add(new SampleItem("state2", "State 2", null));
        sampleItems.add(new SampleItem("state3", "State 3", null));
        select.setItems(sampleItems);
        select.setItemLabelGenerator(item -> ((SampleItem) item).label());
        select.setItemEnabledProvider(item -> !Boolean.TRUE.equals(((SampleItem) item).disabled()));
    }
}
