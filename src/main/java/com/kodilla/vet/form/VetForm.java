package com.kodilla.vet.form;

import com.kodilla.vet.client.VetClient;
import com.kodilla.vet.domain.VetDto;
import com.kodilla.vet.view.VetView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class VetForm extends FormLayout  {

    private TextField name = new TextField("Name");
    private TextField location = new TextField("Location");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Button update = new Button("Update");
    private VetView vetView;
    private VetClient vetClient;
    private Binder<VetDto> binder = new Binder<VetDto>(VetDto.class);
    private VetDto vetDto;

    public VetForm(VetView vetView) {
        HorizontalLayout buttons = new HorizontalLayout(save, update, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(name,location, buttons);
        this.vetView = vetView;
        save.addClickListener(event -> save());
        update.addClickListener(event -> update(vetDto));
        delete.addClickListener(event -> delete(vetDto.getId()));
    }

    public void save(){
        VetDto vet = binder.getBean();
        vetClient.addVet(vet);
        vetView.refresh();
        setVet(null);
    }

    public void delete(Long vetId) {
        VetDto vet = binder.getBean();
        vetClient.deleteVet(vetId);
        vetView.refresh();
        setVet(null);
    }

    public void update(VetDto vet) {
        vetClient.updateVet(vet);
        vetView.refresh();
        setVet(null);
    }

    public void setVet(VetDto vet) {
        binder.setBean(vet);

        if(vet == null) {
            setVisible(false);
        } else {
            setVisible(true);
            name.focus();
        }
    }

}
