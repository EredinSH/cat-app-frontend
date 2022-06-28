package com.kodilla.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class AppConfig {

    @Value("${cat.api.endpoint}")
    private String catEndpoint;

    @Value("${google.api.endpoint}")
    private String googleEndpoint;

    @Value("${information.api.endpoint}")
    private String informationEndpoint;

    @Value("${vet.api.endpoint}")
    private String vetEndpoint;

    @Value("${volunteer.api.endpoint}")
    private String volunteerEndpoint;

    @Value("${pdf.api.endpoint}")
    private String pdfEndpoint;

}
