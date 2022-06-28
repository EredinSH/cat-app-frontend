package com.kodilla.information.domain;

import com.kodilla.cat.domain.CatDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformationDto {

    private Long id;
    private String category;
    private String content;

    public List<InformationDto> exampleInformations() {
        List<InformationDto> informationList = new ArrayList<>();
        informationList.add(new InformationDto(1L, "Hair", "brush me"));
        informationList.add(new InformationDto(2L, "Wash", "wash me"));
        informationList.add(new InformationDto(3L, "Clothes", "hat"));
        informationList.add(new InformationDto(4L, "Food", "meat"));
        informationList.add(new InformationDto(5L, "Fun", "ball"));

        return informationList;
    }

    public List<InformationDto> findByCategory(String category) {
        List<InformationDto> filteredList = exampleInformations().stream().filter(info -> info.getCategory().contains(category)).collect(Collectors.toList());

        return filteredList;
    }

}
