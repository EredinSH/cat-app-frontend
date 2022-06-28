package com.kodilla.cat.client;

import com.kodilla.config.AppConfig;
import com.kodilla.cat.domain.CatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CatClient {

    private final RestTemplate restTemplate;
    private final AppConfig appConfig;

    public List<CatDto> getCats() {
        try {
            URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getCatEndpoint()).build().encode().toUri();
            CatDto[] result = restTemplate.getForObject(url, CatDto[].class);
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

    public CatDto getCatById(Long catId) {
        URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getCatEndpoint() + "/" + catId)
                .build().encode().toUri();
        CatDto result = restTemplate.getForObject(url, CatDto.class);
        return result;
    }

    public void addCat(CatDto catDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getCatEndpoint()).build().encode().toUri();
        restTemplate.postForObject(url, catDto, CatDto.class);
    }


    public void updateCat(CatDto catDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getCatEndpoint()).build().encode().toUri();
        restTemplate.put(url, catDto);
    }

    public void deleteCat(Long catId) {
        URI url = UriComponentsBuilder.fromHttpUrl(appConfig.getCatEndpoint() + "/" + catId)
                .build().encode().toUri();
        restTemplate.delete(url);
    }



}
