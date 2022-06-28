package com.kodilla.information.client;

import com.kodilla.config.AppConfig;
import com.kodilla.information.domain.InformationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InformationClient {

    private final RestTemplate restTemplate;
    private final AppConfig appConfig;

    public List<InformationDto> getInformations() {
        try {
            URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getInformationEndpoint()).build().encode().toUri();
            InformationDto[] result = restTemplate.getForObject(url, InformationDto[].class);
            return Optional.ofNullable(result)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(p -> Objects.nonNull(p.getId()) && Objects.nonNull(p.getCategory()) && Objects.nonNull(p.getContent()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public InformationDto getInformationById(Long informationId) {
        URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getInformationEndpoint() + "/" + informationId)
                .build().encode().toUri();
        InformationDto result = restTemplate.getForObject(url, InformationDto.class);
        return result;
    }

    public void addInformation(InformationDto informationDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getInformationEndpoint()).build().encode().toUri();
        restTemplate.postForObject(url, informationDto, InformationDto.class);
    }


    public void updateInformation(InformationDto informationDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getInformationEndpoint()).build().encode().toUri();
        restTemplate.put(url, informationDto);
    }

    public void deleteInformation(Long informationId) {
        URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getInformationEndpoint() + "/" + informationId)
                .build().encode().toUri();
        restTemplate.delete(url);
    }

}
