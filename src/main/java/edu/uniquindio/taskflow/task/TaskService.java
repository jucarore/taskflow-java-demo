package edu.uniquindio.taskflow.task;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task create(String title) {
        return repository.save(new Task(title));
    }

    public List<Task> list() {
        return repository.findAll();
    }
}
