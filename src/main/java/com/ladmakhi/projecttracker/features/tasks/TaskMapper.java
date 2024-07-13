package com.ladmakhi.projecttracker.features.tasks;

import com.ladmakhi.projecttracker.features.tasks.dtos.GetTaskDetailDto;
import com.ladmakhi.projecttracker.features.tasks.dtos.GetTaskDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {
    GetTaskDetailDto mapTaskToGetTaskDetailDto(Task task);

    GetTaskDto mapTaskToGetTaskDto(Task task);

    List<GetTaskDto> mapListOfTaskToListOfGetTaskDto(List<Task> tasks);
}
