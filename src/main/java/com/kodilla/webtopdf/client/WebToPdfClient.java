package com.kodilla.webtopdf.client;

import com.kodilla.config.AppConfig;
import com.kodilla.webtopdf.domain.WebToPdfDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WebToPdfClient {

    private final RestTemplate restTemplate;
    private final AppConfig appConfig;

    public List<WebToPdfDto> getWebToPdf(String name) {
        URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getPdfEndpoint())
                .queryParam("url",name)
                .build().encode().toUri();

        try {
            WebToPdfDto[] result = restTemplate.getForObject(url,WebToPdfDto[].class);
            return Optional.ofNullable(result)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(p -> Objects.nonNull(p.getRequestedUrl()) && Objects.nonNull(p.getPdfUrl()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return Collections.emptyList();
        }

    }

}
