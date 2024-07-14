package com.ladmakhi.projecttracker.features.tasks;

import com.ladmakhi.projecttracker.features.collection.Collection;
import com.ladmakhi.projecttracker.features.collection.CollectionService;
import com.ladmakhi.projecttracker.features.comments.Comment;
import com.ladmakhi.projecttracker.features.tasks.dtos.*;
import com.ladmakhi.projecttracker.features.users.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    private final CollectionService collectionService;

    private final EntityManager entityManager;

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

    @Override
    public List<GetTaskDetailDto> searchTasks(SearchTaskDto dto) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> query = builder.createQuery(Task.class);
        Root<Task> root = query.from(Task.class);
        List<Predicate> predicates = new ArrayList<>();

        dto.getTitle().ifPresent(title -> {
            Predicate condition = builder.equal(root.get("title"), title);
            predicates.add(condition);
        });

        dto.getDescription().ifPresent(description -> {
            Predicate condition = builder.like(root.get("description"), description + "%");
            predicates.add(condition);
        });

        dto.getCreatorId().ifPresent(creatorId -> {
            Predicate condition = builder.equal(root.get("creator").get("id"), creatorId);
            predicates.add(condition);
        });

        dto.getCollectionId().ifPresent(collectionId -> {
            Predicate condition = builder.equal(root.get("collection").get("id"), collectionId);
            predicates.add(condition);
        });


        if (dto.getReminderDateGte().isPresent() && dto.getReminderDateLte().isPresent()) {
            LocalDate endDate = dto.getReminderDateLte().get();
            LocalDate startDate = dto.getReminderDateGte().get();
            Predicate condition = builder.and(
                    builder.greaterThanOrEqualTo(root.get("reminderDate"), startDate),
                    builder.lessThanOrEqualTo(root.get("reminderDate"), endDate)
            );
            predicates.add(condition);
        } else if (dto.getReminderDateGte().isPresent()) {
            LocalDate reminderDate = dto.getReminderDateGte().get();
            Predicate condition = builder.greaterThanOrEqualTo(root.get("reminderDate"), reminderDate);
            predicates.add(condition);
        } else if (dto.getReminderDateLte().isPresent()) {
            LocalDate reminderDate = dto.getReminderDateLte().get();
            Predicate condition = builder.lessThanOrEqualTo(root.get("reminderDate"), reminderDate);
            predicates.add(condition);
        }
        query.select(root).where(predicates.toArray(new Predicate[0]));
        List<Task> tasks = (List<Task>) entityManager.createQuery(query).getResultList();
        return taskMapper.mapListOfTaskToListOfGetTaskDetailDto(tasks);
    }
}
