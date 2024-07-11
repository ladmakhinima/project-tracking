package com.ladmakhi.projecttracker.features.tasks;

import com.ladmakhi.projecttracker.core.annotations.GetCurrentUser;
import com.ladmakhi.projecttracker.features.tasks.dtos.CreateTaskDto;
import com.ladmakhi.projecttracker.features.tasks.dtos.GetTaskDetailDto;
import com.ladmakhi.projecttracker.features.tasks.dtos.GetTaskDto;
import com.ladmakhi.projecttracker.features.tasks.dtos.UpdateTaskDto;
import com.ladmakhi.projecttracker.features.users.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

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
