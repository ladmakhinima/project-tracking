package com.ladmakhi.projecttracker.features.tasks;

import com.ladmakhi.projecttracker.features.tasks.dtos.GetTaskDetailDto;
import com.ladmakhi.projecttracker.features.tasks.dtos.GetTaskDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TaskMapper {
    GetTaskDetailDto mapTaskToGetTaskDetailDto(Task task);
    GetTaskDto mapTaskToGetTaskDto(Task task);
    List<GetTaskDto> mapListOfTaskToListOfGetTaskDto(List<Task> tasks);
}
