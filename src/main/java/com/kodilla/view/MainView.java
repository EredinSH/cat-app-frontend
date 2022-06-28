package com.kodilla.view;

import com.kodilla.cat.view.CatView;
import com.kodilla.google.view.GoogleView;
import com.kodilla.information.view.InformationView;
import com.kodilla.vet.view.VetView;
import com.kodilla.volunteer.view.VolunteerView;
import com.kodilla.webtopdf.view.WebToPdfView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class MainView extends VerticalLayout {

    @Autowired
    private final CatView catView;
    @Autowired
    private final GoogleView googleView;
    @Autowired
    private final InformationView informationView;
    @Autowired
    private final VetView vetView;
    @Autowired
    private final VolunteerView volunteerView;
    @Autowired
    private final WebToPdfView webToPdfView;

    private final Tab catsList = new Tab("CATS");
    private final Tab googleList = new Tab("GOOGLE SEARCH");
    private final Tab informationsList = new Tab("INFORMATIONS");
    private final Tab vetsList = new Tab("VETS");
    private final Tab volunteersList = new Tab("VOLUNTEERS");
    private final Tab pdfsList = new Tab("LINK TO PDF");

    public MainView(CatView catView, GoogleView googleView, InformationView informationView, VetView vetView, VolunteerView volunteerView, WebToPdfView webToPdfView, Component... children) {
        super(children);
        this.catView = catView;
        this.googleView = googleView;
        this.informationView = informationView;
        this.vetView = vetView;
        this.volunteerView = volunteerView;
        this.webToPdfView = webToPdfView;

        add(catView,catsList);
        add(googleView, googleList);
        add(informationView, informationsList);
        add(vetView, vetsList);
        add(volunteerView, volunteersList);
        add(webToPdfView, pdfsList);
    }
}
