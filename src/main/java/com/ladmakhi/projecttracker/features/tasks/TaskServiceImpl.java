package com.ladmakhi.projecttracker.features.tasks;

import com.ladmakhi.projecttracker.features.collection.Collection;
import com.ladmakhi.projecttracker.features.collection.CollectionService;
import com.ladmakhi.projecttracker.features.comments.Comment;
import com.ladmakhi.projecttracker.features.tasks.dtos.CreateTaskDto;
import com.ladmakhi.projecttracker.features.tasks.dtos.GetTaskDetailDto;
import com.ladmakhi.projecttracker.features.tasks.dtos.GetTaskDto;
import com.ladmakhi.projecttracker.features.tasks.dtos.UpdateTaskDto;
import com.ladmakhi.projecttracker.features.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    private final CollectionService collectionService;

    @Override
    public GetTaskDto createTask(CreateTaskDto dto, User creator) throws Exception {
        boolean existByTitle = taskRepository.existsByTitle(dto.getTitle());
        if (existByTitle) {
            throw new Exception("Duplicated Title");
        }
        Collection collection = collectionService.getCollectionById(dto.getCollectionId());
        var task = Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .attachments(dto.getAttachments())
                .creator(creator)
                .collection(collection);
        if (dto.getReminderDate() != null) {
            task.reminderDate(LocalDate.parse(dto.getReminderDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        }
        Task createdTask = taskRepository.save(task.build());
        return taskMapper.mapTaskToGetTaskDto(createdTask);
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
        if (!task.getTitle().equals(dto.getTitle())) {
            boolean checkNewTitleDuplication = taskRepository.existsByTitleAndIdNot(dto.getTitle(), id);
            if (checkNewTitleDuplication) {
                throw new Exception("Duplicated Title");
            }
        }
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setAttachments(dto.getAttachments());
        if (dto.getReminderDate() != null) {
            task.setReminderDate(LocalDate.parse(dto.getReminderDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy")));
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
        List<Comment> comments = task.getComments().stream().filter(e -> e.getParent() == null).toList();
        task.setComments(comments);
        return taskMapper.mapTaskToGetTaskDetailDto(task);
    }

    @Override
    public Task getTaskById(Long id) throws Exception {
        return taskRepository.findById(id).orElseThrow(() -> new Exception("Task Is Not Found"));
    }
}
