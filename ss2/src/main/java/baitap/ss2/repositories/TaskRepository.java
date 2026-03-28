package baitap.ss2.repositories;

import org.springframework.stereotype.Repository;
import baitap.ss2.models.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class TaskRepository implements ITaskRepository {

    private List<Task> tasks = new ArrayList<>(List.of(
            new Task(1, "Fix bug", "Sửa lỗi đăng nhập", "HIGH", 1),
            new Task(2, "Viết docs", "Tài liệu API","MEDIUM", 2),
            new Task(3, "Triển khai", "Deploy lên server", "HIGH", 3),
            new Task(4, "Test", "Unit test", "LOW", 1),
            new Task(5, "Thiết kế UI", "Trang chủ", "MEDIUM", 2),
            new Task(6, "Migration DB", "Update schema", "HIGH", 3),
            new Task(7, "Review code", "Check PR", "LOW", 1),
            new Task(8, "Tối ưu query", "SQL performance", "HIGH", 2),
            new Task(9, "Audit bảo mật", "Kiểm tra lỗ hổng", "HIGH", 3),
            new Task(10, "Meeting", "Thảo luận dự án", "LOW", 1)
    ));

    @Override
    public List<Task> findAll() {
        return tasks;
    }

    @Override
    public Task save(Task task) {
        task.setId((tasks.size() + 1));
        tasks.add(task);
        return task;
    }

    @Override
    public Task findById(int id) {
        return tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Task update(int id, Task task) {
        Task existing = findById(id);

        if (existing == null) return null;

        existing.setTitle(task.getTitle());
        existing.setAssignedTo(task.getAssignedTo());

        return existing;
    }

    @Override
    public Task deleteById(int id) {
        Task existing = findById(id);

        if (existing == null) return null;

        tasks.remove(existing);
        return existing;
    }
}