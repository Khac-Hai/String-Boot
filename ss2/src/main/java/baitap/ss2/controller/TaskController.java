package baitap.ss2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import baitap.ss2.models.Task;
import baitap.ss2.services.TaskService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<?> getAllTasks(@RequestParam(required = false) String search) {
        List<Task> tasks = taskService.findAllTasks();
        if (search != null && !search.isEmpty()) {
            tasks = tasks.stream()
                    .filter(t -> t.getTitle().toLowerCase()
                            .contains(search.toLowerCase()))
                    .collect(Collectors.toList());
        }

        return ResponseEntity.ok(tasks); // 200 OK

    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task newTask) {
        if (newTask.getTitle() == null ||newTask.getTitle().isBlank() || newTask.getAssignedTo() <= 0) {
            return ResponseEntity.badRequest().body("Missing required fields");
        }

        Task createdTask = taskService.createTask(newTask);

        if (createdTask == null) {
            return ResponseEntity
                    .badRequest()
                    .body("User not found");
        }

        return ResponseEntity
                .status(201)
                .body(createdTask);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable int id, @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(id, task);

        if (updatedTask == null) {
            return ResponseEntity.status(404).body("Task not found");
        }

        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable int id) {
        Task deletedTask = taskService.deleteTask(id);

        if (deletedTask == null) {
            return ResponseEntity.status(404).body("Task not found");
        }

        return ResponseEntity.noContent().build(); // 204
    }
}