package edu.uniquindio.taskflow.task;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(@Valid @RequestBody CreateTaskRequest request) {
        Task task = service.create(request.title(), request.dueDate());
        return ResponseEntity.created(URI.create("/api/tasks/" + task.getId()))
                .body(TaskResponse.from(task));
    }

    @GetMapping
    public List<TaskResponse> list() {
        return service.list().stream().map(TaskResponse::from).toList();
    }

    public record CreateTaskRequest(
            @NotBlank(message = "El título es obligatorio") String title,
            LocalDate dueDate) {
    }

    public record TaskResponse(Long id, String title, LocalDate dueDate) {
        static TaskResponse from(Task task) {
            return new TaskResponse(task.getId(), task.getTitle(), task.getDueDate());
        }
    }
}
