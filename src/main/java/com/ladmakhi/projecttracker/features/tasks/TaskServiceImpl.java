package com.ladmakhi.projecttracker.features.tasks;

import com.ladmakhi.projecttracker.features.collection.Collection;
import com.ladmakhi.projecttracker.features.collection.CollectionService;
import com.ladmakhi.projecttracker.features.tasks.dtos.CreateTaskDto;
import com.ladmakhi.projecttracker.features.tasks.dtos.GetTaskDetailDto;
import com.ladmakhi.projecttracker.features.tasks.dtos.GetTaskDto;
import com.ladmakhi.projecttracker.features.tasks.dtos.UpdateTaskDto;
import com.ladmakhi.projecttracker.features.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private CollectionService collectionService;

    @Override
    public GetTaskDto createTask(CreateTaskDto dto, User creator) throws Exception {
        boolean existByTitle = taskRepository.existsByTitle(dto.title());
        if (existByTitle) {
            throw new Exception("Duplicated Title");
        }
        Collection collection = collectionService.getCollectionById(dto.collectionId());
        Task task = new Task();
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setAttachments(dto.attachments());
        task.setCreator(creator);
        if (dto.reminderDate() != null) {
            task.setReminderDate(LocalDate.parse(dto.reminderDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        }
        task.setCollection(collection);
        task = taskRepository.save(task);
        return taskMapper.mapTaskToGetTaskDto(task);
    }

    @Override
    public GetTaskDto deleteTaskById(Long id) throws Exception {
        Task task = getTaskById(id);
        taskRepository.delete(task);
        return taskMapper.mapTaskToGetTaskDto(task);
    }

    @Override
    public GetTaskDto updateTaskById(Long id, UpdateTaskDto dto) throws Exception {
        Task task = getTaskById(id);
        if (task.getTitle() != dto.title()) {
            boolean checkNewTitleDuplication = taskRepository.existsByTitleAndIdNot(dto.title(), id);
            if (checkNewTitleDuplication) {
                throw new Exception("Duplicated Title");
            }
        }
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setAttachments(dto.attachments());
        if (dto.reminderDate() != null) {
            task.setReminderDate(LocalDate.parse(dto.reminderDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        }
        task = taskRepository.save(task);
        return taskMapper.mapTaskToGetTaskDto(task);
    }

    @Override
    public List<GetTaskDto> getTasks() {
        List<Task> tasks = taskRepository.findAll();
        return taskMapper.mapListOfTaskToListOfGetTaskDto(tasks);
    }

    @Override
    public GetTaskDetailDto getTaskDetailById(Long id) throws Exception {
        Task task = getTaskById(id);
        return taskMapper.mapTaskToGetTaskDetailDto(task);
    }

    @Override
    public Task getTaskById(Long id) throws Exception {
        return taskRepository.findById(id).orElseThrow(() -> new Exception("Task Is Not Found"));
    }
}
