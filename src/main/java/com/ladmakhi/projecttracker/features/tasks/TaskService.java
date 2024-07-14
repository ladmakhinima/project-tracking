package com.ladmakhi.projecttracker.features.tasks;

import com.ladmakhi.projecttracker.features.tasks.dtos.*;
import com.ladmakhi.projecttracker.features.users.User;

import java.util.List;

public interface TaskService {
    GetTaskDto createTask(CreateTaskDto dto, User creator) throws Exception;

    GetTaskDto deleteTaskById(Long id) throws Exception;

    GetTaskDto updateTaskById(Long id, UpdateTaskDto dto) throws Exception;

    List<GetTaskDto> getTasks();

    GetTaskDetailDto getTaskDetailById(Long id) throws Exception;

    Task getTaskById(Long id) throws Exception;

    List<GetTaskDetailDto> searchTasks(SearchTaskDto dto);
}
