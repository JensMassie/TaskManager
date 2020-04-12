package pe.task.service;

import org.springframework.stereotype.Service;
import pe.task.domain.SubTask;
import pe.task.domain.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<Task> getTasks();
    void addTask(Task task);
    Task getTaskFromId(UUID id);
    void addSubTaskToTask(UUID uuid, SubTask subTask);
    void editTask(Task task, String id);
    void saveAndFlushTaskToRepo(Task task);
}
