package com.ladmakhi.projecttracker.features.tasks;

import com.ladmakhi.projecttracker.features.tasks.dtos.GetTaskDetailDto;
import com.ladmakhi.projecttracker.features.tasks.dtos.GetTaskDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskMapper {
    public GetTaskDetailDto mapTaskToGetTaskDetailDto(Task task) {
        return new GetTaskDetailDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getReminderDate(),
                task.getAttachments(),
                task.getCollection(),
                task.getFunctors()
        );
    }

    public GetTaskDto mapTaskToGetTaskDto(Task task) {
        return new GetTaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getReminderDate(),
                task.getAttachments()
        );
    }

    public List<GetTaskDto> mapListOfTaskToListOfGetTaskDto(List<Task> tasks) {
        return tasks.stream().map(this::mapTaskToGetTaskDto).toList();
    }
}
