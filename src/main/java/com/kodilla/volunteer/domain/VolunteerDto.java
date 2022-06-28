package com.kodilla.volunteer.domain;

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
public class VolunteerDto {

    private Long id;
    private String name;
    private String age;
    private String description;

    public List<VolunteerDto> exampleVolunteers() {
        List<VolunteerDto> volunteerList = new ArrayList<>();
        volunteerList.add(new VolunteerDto(1L, "Suguru Geto", "27", "Użytkownik klątwy"));
        volunteerList.add(new VolunteerDto(2L, "Satoru Gojo", "28", "Nauczyciel"));
        volunteerList.add(new VolunteerDto(3L, "Kento Nanami", "28", "Czarownik jujutsu"));
        volunteerList.add(new VolunteerDto(4L, "Utahime Iori", "31", "Nauczycielka"));
        volunteerList.add(new VolunteerDto(5L, "Kasumi Miwa", "17", "Uczennica"));

        return volunteerList;
    }

    public List<VolunteerDto> findByName(String name) {
        List<VolunteerDto> filteredList = exampleVolunteers().stream().filter(volunteer -> volunteer.getName().contains(name)).collect(Collectors.toList());

        return filteredList;
    }

}
