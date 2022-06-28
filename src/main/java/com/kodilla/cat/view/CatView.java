package com.kodilla.cat.view;

import com.kodilla.cat.form.CatForm;
import com.kodilla.cat.domain.CatDto;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.springframework.stereotype.Component;

@Component
public class CatView extends VerticalLayout {

    private TextField filter = new TextField();

    private Button addNewCat = new Button("Add new cat");

    private CatDto catDto;
    private Grid<CatDto> grid = new Grid<>(CatDto.class);

    private CatForm form = new CatForm(this);

    public CatView() {
        filter.setPlaceholder("Filtered by name");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update());
        grid.setColumns("id","name","age","description");

        addNewCat.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setCat(new CatDto());
        });
        HorizontalLayout toolbar = new HorizontalLayout(filter, addNewCat);

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        form.setCat(null);
        setSizeFull();
        refresh();

        grid.asSingleSelect().addValueChangeListener(event -> form.setCat(grid.asSingleSelect().getValue()));

    }

    public void refresh() {
        grid.setItems(catDto.exampleCats());
    }

    private void update() {
        grid.setItems(catDto.findByName(filter.getValue()));
    }

}
