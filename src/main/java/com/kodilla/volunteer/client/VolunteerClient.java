package com.kodilla.volunteer.client;

import com.kodilla.config.AppConfig;
import com.kodilla.volunteer.domain.VolunteerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VolunteerClient {

    private final RestTemplate restTemplate;
    private final AppConfig appConfig;

    public List<VolunteerDto> getVolunteers() {
        try {
            URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getVolunteerEndpoint()).build().encode().toUri();
            VolunteerDto[] result = restTemplate.getForObject(url, VolunteerDto[].class);
            return Optional.ofNullable(result)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(p -> Objects.nonNull(p.getId()) && Objects.nonNull(p.getName()) && Objects.nonNull(p.getAge()) && Objects.nonNull(p.getDescription()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public VolunteerDto getVolunteerById(Long volunteerId) {
        URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getVolunteerEndpoint() + "/" + volunteerId)
                .build().encode().toUri();
        VolunteerDto result = restTemplate.getForObject(url, VolunteerDto.class);
        return result;
    }

    public void addVolunteer(VolunteerDto volunteerDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getVolunteerEndpoint()).build().encode().toUri();
        restTemplate.postForObject(url, volunteerDto, VolunteerDto.class);
    }


    public void updateVolunteer(VolunteerDto volunteerDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getVolunteerEndpoint()).build().encode().toUri();
        restTemplate.put(url, volunteerDto);
    }

    public void deleteVolunteer(Long volunteerId) {
        URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getVolunteerEndpoint() + "/" + volunteerId)
                .build().encode().toUri();
        restTemplate.delete(url);
    }

}
