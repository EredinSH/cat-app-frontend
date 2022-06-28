package com.kodilla.information.form;

import com.kodilla.information.client.InformationClient;
import com.kodilla.information.domain.InformationDto;
import com.kodilla.information.view.InformationView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class InformationForm extends FormLayout  {

    private TextField category = new TextField("Category");
    private TextField content = new TextField("Content");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Button update = new Button("Update");
    private InformationView informationView;
    private InformationClient informationClient;
    private Binder<InformationDto> binder = new Binder<InformationDto>(InformationDto.class);
    private InformationDto informationDto;

    public InformationForm(InformationView informationView) {
        HorizontalLayout buttons = new HorizontalLayout(save, update, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(category, content, buttons);
        this.informationView = informationView;
        save.addClickListener(event -> save());
        update.addClickListener(event -> update(informationDto));
        delete.addClickListener(event -> delete(informationDto.getId()));
    }

    public void save(){
        InformationDto information = binder.getBean();
        informationClient.addInformation(information);
        informationView.refresh();
        setInformation(null);
    }

    public void delete(Long infformationId) {
        InformationDto information = binder.getBean();
        informationClient.deleteInformation(infformationId);
        informationView.refresh();
        setInformation(null);
    }

    public void update(InformationDto information) {
        informationClient.updateInformation(information);
        informationView.refresh();
        setInformation(null);
    }

    public void setInformation(InformationDto information) {
        binder.setBean(information);

        if(information == null) {
            setVisible(false);
        } else {
            setVisible(true);
            category.focus();
        }
    }

}
