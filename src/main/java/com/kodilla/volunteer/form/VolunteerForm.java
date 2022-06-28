package com.kodilla.volunteer.form;

import com.kodilla.volunteer.client.VolunteerClient;
import com.kodilla.volunteer.domain.VolunteerDto;
import com.kodilla.volunteer.view.VolunteerView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class VolunteerForm extends FormLayout  {

    private TextField name = new TextField("Name");
    private TextField age = new TextField("Age");
    private TextField description = new TextField("Description");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Button update = new Button("Update");
    private VolunteerView volunteerView;
    private VolunteerClient volunteerClient;
    private Binder<VolunteerDto> binder = new Binder<VolunteerDto>(VolunteerDto.class);
    private VolunteerDto volunteerDto;

    public VolunteerForm(VolunteerView volunteerView) {
        HorizontalLayout buttons = new HorizontalLayout(save, update, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(name,age,description, buttons);
        this.volunteerView = volunteerView;
        save.addClickListener(event -> save());
        update.addClickListener(event -> update(volunteerDto));
        delete.addClickListener(event -> delete(volunteerDto.getId()));
    }

    public void save(){
        VolunteerDto volunteer = binder.getBean();
        volunteerClient.addVolunteer(volunteer);
        volunteerView.refresh();
        setVolunteer(null);
    }

    public void delete(Long volunteerId) {
        VolunteerDto volunteer = binder.getBean();
        volunteerClient.deleteVolunteer(volunteerId);
        volunteerView.refresh();
        setVolunteer(null);
    }

    public void update(VolunteerDto volunteer) {
        volunteerClient.updateVolunteer(volunteer);
        volunteerView.refresh();
        setVolunteer(null);
    }

    public void setVolunteer(VolunteerDto volunteer) {
        binder.setBean(volunteer);

        if(volunteer == null) {
            setVisible(false);
        } else {
            setVisible(true);
            name.focus();
        }
    }

}
