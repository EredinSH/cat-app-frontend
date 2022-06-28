package com.kodilla.vet.client;

import com.kodilla.config.AppConfig;
import com.kodilla.vet.domain.VetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VetClient {

    private final RestTemplate restTemplate;
    private final AppConfig appConfig;

    public List<VetDto> getVets() {
        try {
            URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getVetEndpoint()).build().encode().toUri();
            VetDto[] result = restTemplate.getForObject(url, VetDto[].class);
            return Optional.ofNullable(result)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(p -> Objects.nonNull(p.getId()) && Objects.nonNull(p.getName()) && Objects.nonNull(p.getLocation()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public VetDto getVetById(Long vetId) {
        URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getVetEndpoint() + "/" + vetId)
                .build().encode().toUri();
        VetDto result = restTemplate.getForObject(url, VetDto.class);
        return result;
    }

    public void addVet(VetDto vetDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getVetEndpoint()).build().encode().toUri();
        restTemplate.postForObject(url, vetDto, VetDto.class);
    }


    public void updateVet(VetDto vetDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getVetEndpoint()).build().encode().toUri();
        restTemplate.put(url, vetDto);
    }

    public void deleteVet(Long vetId) {
        URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getVetEndpoint() + "/" + vetId)
                .build().encode().toUri();
        restTemplate.delete(url);
    }

}
