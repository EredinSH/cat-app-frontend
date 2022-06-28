package com.kodilla.volunteer.view;

import com.kodilla.volunteer.domain.VolunteerDto;
import com.kodilla.volunteer.form.VolunteerForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.springframework.stereotype.Component;

@Component
public class VolunteerView extends VerticalLayout  {

    private TextField filter = new TextField();

    private Button addNewVolunteer = new Button("Add new volunteer");

    private VolunteerDto volunteerDto;
    private Grid<VolunteerDto > grid = new Grid<>(VolunteerDto .class);

    private VolunteerForm form = new VolunteerForm(this);

    public VolunteerView() {
        filter.setPlaceholder("Filtered by name");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update());
        grid.setColumns("id","name","age","description");

        addNewVolunteer.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setVolunteer(new VolunteerDto());
        });
        HorizontalLayout toolbar = new HorizontalLayout(filter, addNewVolunteer);

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        form.setVolunteer(null);
        setSizeFull();
        refresh();

        grid.asSingleSelect().addValueChangeListener(event -> form.setVolunteer(grid.asSingleSelect().getValue()));

    }

    public void refresh() {
        grid.setItems(volunteerDto.exampleVolunteers());
    }

    private void update() {
        grid.setItems(volunteerDto.findByName(filter.getValue()));
    }

}
