package com.kodilla.google.form;

import com.kodilla.cat.domain.CatDto;
import com.kodilla.google.client.GoogleClient;
import com.kodilla.google.domain.GoogleSearchDto;
import com.kodilla.google.view.GoogleView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class GoogleForm extends FormLayout  {

    private TextField siteAddress = new TextField("Site address:");
    private Button search = new Button("Search");

    private Binder<GoogleSearchDto> binder = new Binder<GoogleSearchDto>(GoogleSearchDto.class);
    private String name;
    private GoogleClient googleClient;

    private GoogleView googleView;
    private GoogleSearchDto googleSearchDto;

    public GoogleForm(GoogleView googleView) {
        HorizontalLayout buttons = new HorizontalLayout(search);
        search.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(siteAddress, buttons);
        this.googleView = googleView;
        search.addClickListener(event -> search(name));
    }

    public void search(String name) {
        GoogleSearchDto googleSearchDto = binder.getBean();
        googleClient.getGoogleSearch(name);
        googleView.refresh();
    }

    public void setGoogle(GoogleSearchDto google) {
        binder.setBean(google);

        if(google == null) {
            setVisible(false);
        } else {
            setVisible(true);
            googleSearchDto.getLink();
        }
    }

}
