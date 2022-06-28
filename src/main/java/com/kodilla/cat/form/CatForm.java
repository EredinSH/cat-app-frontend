package com.kodilla.cat.form;

import com.kodilla.cat.client.CatClient;
import com.kodilla.cat.view.CatView;
import com.kodilla.cat.domain.CatDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class CatForm extends FormLayout {

    private TextField name = new TextField("Name");
    private TextField age = new TextField("Age");
    private TextField description = new TextField("Description");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Button update = new Button("Update");
    private CatView catView;
    private CatClient catClient;
    private Binder<CatDto> binder = new Binder<CatDto>(CatDto.class);
    private CatDto catDto;

    public CatForm(CatView catView) {
        HorizontalLayout buttons = new HorizontalLayout(save, update, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(name,age,description, buttons);
        this.catView = catView;
        save.addClickListener(event -> save());
        update.addClickListener(event -> update(catDto));
        delete.addClickListener(event -> delete(catDto.getId()));
    }

    public void save(){
        CatDto cat = binder.getBean();
        catClient.addCat(cat);
        catView.refresh();
        setCat(null);
    }

    public void delete(Long catId) {
        CatDto cat = binder.getBean();
        catClient.deleteCat(catId);
        catView.refresh();
        setCat(null);
    }

    public void update(CatDto cat) {
        catClient.updateCat(cat);
        catView.refresh();
        setCat(null);
    }

    public void setCat(CatDto cat) {
        binder.setBean(cat);

        if(cat == null) {
            setVisible(false);
        } else {
            setVisible(true);
            name.focus();
        }
    }
}
