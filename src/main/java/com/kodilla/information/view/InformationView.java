package com.kodilla.information.view;

import com.kodilla.cat.domain.CatDto;
import com.kodilla.cat.form.CatForm;
import com.kodilla.information.domain.InformationDto;
import com.kodilla.information.form.InformationForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.springframework.stereotype.Component;

@Component
public class InformationView extends VerticalLayout  {

    private TextField filter = new TextField();

    private Button addNewInformation = new Button("Add new information");

    private InformationDto informationDto;
    private Grid<InformationDto> grid = new Grid<>(InformationDto.class);

    private InformationForm form = new InformationForm(this);

    public InformationView() {
        filter.setPlaceholder("Filtered by category");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update());
        grid.setColumns("id","category","content");

        addNewInformation.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setInformation(new InformationDto());
        });
        HorizontalLayout toolbar = new HorizontalLayout(filter, addNewInformation);

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        form.setInformation(null);
        setSizeFull();
        refresh();

        grid.asSingleSelect().addValueChangeListener(event -> form.setInformation(grid.asSingleSelect().getValue()));

    }

    public void refresh() {
        grid.setItems(informationDto.exampleInformations());
    }

    private void update() {
        grid.setItems(informationDto.findByCategory(filter.getValue()));
    }

}
