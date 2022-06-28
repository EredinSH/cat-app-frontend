package com.kodilla.webtopdf.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebToPdfDto {

    @JsonProperty("requested_url")
    private String requestedUrl;

    @JsonProperty("pdf_url")
    private String pdfUrl;

    public List<WebToPdfDto> getLinkList() {
        return  new ArrayList<>();
    }

}
