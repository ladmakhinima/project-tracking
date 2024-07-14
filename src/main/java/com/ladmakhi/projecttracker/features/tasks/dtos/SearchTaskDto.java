package com.ladmakhi.projecttracker.features.tasks.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SearchTaskDto {
    public Optional<String> title;
    public Optional<String> description;
    public Optional<LocalDate> reminderDateGte;
    public Optional<LocalDate> reminderDateLte;
    public Optional<Long> collectionId;
    public Optional<Long> creatorId;
}
