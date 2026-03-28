package baitap.ss2.repositories;

import baitap.ss2.models.Task;

import java.util.List;

public interface ITaskRepository {
    List<Task> findAll();
    Task save(Task task);
    Task findById(int id);
    Task update(int id, Task task);
    Task deleteById(int id);
}
