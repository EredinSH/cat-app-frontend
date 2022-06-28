package com.kodilla.google.client;

import com.kodilla.config.AppConfig;
import com.kodilla.google.domain.GoogleSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GoogleClient {

    private final RestTemplate restTemplate;
    private final AppConfig appConfig;

    public List<GoogleSearchDto> getGoogleSearch(String name) {
        URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getGoogleEndpoint())
                .queryParam("q",name)
                .build().encode().toUri();

        try {
            GoogleSearchDto[] searchResult = restTemplate.getForObject(url,GoogleSearchDto[].class);
            return Optional.ofNullable(searchResult)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(p -> Objects.nonNull(p.getLink()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

}
