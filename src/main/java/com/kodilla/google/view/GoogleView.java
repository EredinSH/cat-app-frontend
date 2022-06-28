package com.kodilla.google.view;

import com.kodilla.google.domain.GoogleSearchDto;
import com.kodilla.google.form.GoogleForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.stereotype.Component;

@Component
public class GoogleView extends VerticalLayout {

    private Button getLink = new Button("Get link");

    private GoogleSearchDto googleSearchDto;
    private Grid<GoogleSearchDto> grid = new Grid<>(GoogleSearchDto.class);

    private GoogleForm form = new GoogleForm(this);

    public GoogleView() {
        grid.setColumns("link");

        getLink.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setGoogle(new GoogleSearchDto());
        });
        HorizontalLayout toolbar = new HorizontalLayout(getLink);

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        form.setGoogle(null);
        setSizeFull();
        refresh();

        grid.asSingleSelect().addValueChangeListener(event -> form.setGoogle(grid.asSingleSelect().getValue()));

    }

    public void refresh() {
        grid.setItems(googleSearchDto.getLinkList());
    }

}
