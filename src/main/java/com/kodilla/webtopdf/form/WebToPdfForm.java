package com.kodilla.webtopdf.form;

import com.kodilla.webtopdf.client.WebToPdfClient;
import com.kodilla.webtopdf.domain.WebToPdfDto;
import com.kodilla.webtopdf.view.WebToPdfView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class WebToPdfForm extends FormLayout {

    private TextField requested = new TextField("Your request:");
    private TextField linkToPdf = new TextField("Link to PDF");
    private Button search = new Button("Search");

    private Binder<WebToPdfDto> binder = new Binder<WebToPdfDto>(WebToPdfDto.class);
    private String name;
    private WebToPdfClient webToPdfClient;

    private WebToPdfView webToPdfView;
    private WebToPdfDto webToPdfDto;

    public WebToPdfForm(WebToPdfView webToPdfView) {
        HorizontalLayout buttons = new HorizontalLayout(search);
        search.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(requested, linkToPdf, buttons);
        this.webToPdfView = webToPdfView;
        search.addClickListener(event -> search(name));
    }

    public void search(String name) {
        WebToPdfDto webToPdfDto = binder.getBean();
        webToPdfClient.getWebToPdf(name);
        webToPdfView.refresh();
    }

    public void setWebToPdf(WebToPdfDto webToPdfDto) {
        binder.setBean(webToPdfDto);

        if(webToPdfDto == null) {
            setVisible(false);
        } else {
            setVisible(true);
            webToPdfDto.getRequestedUrl();
            webToPdfDto.getPdfUrl();
        }
    }

}
