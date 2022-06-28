package com.kodilla.cat.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatDto {

    private Long id;
    private String name;
    private String age;
    private String description;

    public List<CatDto> exampleCats() {
        List<CatDto> catList = new ArrayList<>();
        catList.add(new CatDto(1L, "Umbra", "15", "fluffy"));
        catList.add(new CatDto(2L, "Burek", "10", "scared"));
        catList.add(new CatDto(3L, "Regis", "5", "pumpkin"));
        catList.add(new CatDto(4L, "Krzyś", "5", "crazy pie"));
        catList.add(new CatDto(5L, "Scott", "11", "love"));

        return catList;
    }

    public List<CatDto> findByName(String name) {
        List<CatDto> filteredList = exampleCats().stream().filter(cat -> cat.getName().contains(name)).collect(Collectors.toList());

        return filteredList;
    }

}
