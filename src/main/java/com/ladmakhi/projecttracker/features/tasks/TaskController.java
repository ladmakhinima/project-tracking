package com.ladmakhi.projecttracker.features.tasks;

import com.ladmakhi.projecttracker.core.annotations.GetCurrentUser;
import com.ladmakhi.projecttracker.features.tasks.dtos.*;
import com.ladmakhi.projecttracker.features.users.User;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/search")
    public ResponseEntity<?> searchTasks(
            @RequestParam("title") Optional<String> title,
            @RequestParam("description") Optional<String> description,
            @RequestParam("collectionId") Optional<Long> collectionId,
            @RequestParam("creatorId") Optional<Long> creatorId,
            @RequestParam("reminderDate.lte") Optional<LocalDate> reminderDateLte,
            @RequestParam("reminderDate.gte") Optional<LocalDate> reminderDateGte
    ) {
        SearchTaskDto searchDto = new SearchTaskDto(title, description, reminderDateGte, reminderDateLte, collectionId, creatorId);
        List<GetTaskDetailDto> tasks = taskService.searchTasks(searchDto);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody @Valid CreateTaskDto dto,
                                        @GetCurrentUser() User user) throws Exception {
        GetTaskDto responseDto = taskService.createTask(dto, user);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateTaskById(
            @RequestBody @Valid UpdateTaskDto dto,
            @PathVariable("id") Long id) throws Exception {
        GetTaskDto responseDto = taskService.updateTaskById(id, dto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getTasks() {
        List<GetTaskDto> responseDto = taskService.getTasks();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTaskById(@PathVariable("id") Long id) throws Exception {
        GetTaskDto responseDto = taskService.deleteTaskById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getTaskById(@PathVariable("id") Long id) throws Exception {
        GetTaskDetailDto responseDto = taskService.getTaskDetailById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
