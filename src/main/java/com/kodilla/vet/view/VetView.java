package com.kodilla.vet.view;

import com.kodilla.vet.domain.VetDto;
import com.kodilla.vet.form.VetForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.springframework.stereotype.Component;

@Component
public class VetView extends VerticalLayout  {

    private TextField filter = new TextField();

    private Button addNewVet = new Button("Add new vet");

    private VetDto vetDto;
    private Grid<VetDto> grid = new Grid<>(VetDto.class);

    private VetForm form = new VetForm(this);

    public VetView() {
        filter.setPlaceholder("Filtered by name");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update());
        grid.setColumns("id","name","location");

        addNewVet.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setVet(new VetDto());
        });
        HorizontalLayout toolbar = new HorizontalLayout(filter, addNewVet);

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        form.setVet(null);
        setSizeFull();
        refresh();

        grid.asSingleSelect().addValueChangeListener(event -> form.setVet(grid.asSingleSelect().getValue()));

    }

    public void refresh() {
        grid.setItems(vetDto.exampleVets());
    }

    private void update() {
        grid.setItems(vetDto.findByName(filter.getValue()));
    }

}
