package com.kodilla.vet.domain;

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
public class VetDto {

    private Long id;
    private String name;
    private String location;

    public List<VetDto> exampleVets() {
        List<VetDto> vetList = new ArrayList<>();
        vetList.add(new VetDto(1L, "Animals", "Kraków"));
        vetList.add(new VetDto(2L, "Sweet paws", "Warszawa"));
        vetList.add(new VetDto(3L, "Meow", "Poznań"));
        vetList.add(new VetDto(4L, "Wof", "Gdańsk"));
        vetList.add(new VetDto(5L, "Be healthy", "Zakopane"));

        return vetList;
    }

    public List<VetDto> findByName(String name) {
        List<VetDto> filteredList = exampleVets().stream().filter(vet -> vet.getName().contains(name)).collect(Collectors.toList());

        return filteredList;
    }

}
