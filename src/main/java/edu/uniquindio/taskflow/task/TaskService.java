package edu.uniquindio.taskflow.task;

import java.util.List;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task create(String title, LocalDate dueDate) {
        return repository.save(new Task(title, dueDate));
    }

    public List<Task> list() {
        return repository.findAll();
    }
}
