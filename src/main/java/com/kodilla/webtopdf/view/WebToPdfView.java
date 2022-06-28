package com.kodilla.webtopdf.view;

import com.kodilla.webtopdf.domain.WebToPdfDto;
import com.kodilla.webtopdf.form.WebToPdfForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.stereotype.Component;

@Component
public class WebToPdfView extends VerticalLayout {

    private Button requestedLink = new Button("Requested Link");
    private Button linkToPdf = new Button("Link to PDF");

    private WebToPdfDto webToPdfDto;
    private Grid<WebToPdfDto> grid = new Grid<>(WebToPdfDto.class);

    private WebToPdfForm form = new WebToPdfForm(this);

    public WebToPdfView() {
        grid.setColumns("requested link","link to pdf");

        requestedLink.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setWebToPdf(new WebToPdfDto());
        });
        HorizontalLayout toolbar = new HorizontalLayout(requestedLink);

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        form.setWebToPdf(null);
        setSizeFull();
        refresh();

        grid.asSingleSelect().addValueChangeListener(event -> form.setWebToPdf(grid.asSingleSelect().getValue()));

    }

    public void refresh() {
        grid.setItems(webToPdfDto.getLinkList());
    }

}
