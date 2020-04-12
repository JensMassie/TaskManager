package pe.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.task.domain.SubTask;
import pe.task.domain.Task;
import pe.task.domain.TaskRepository;
import pe.task.domain.TempDb;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private final TaskRepository repo;

    public TaskServiceImpl(TaskRepository repo) {
        this.repo = repo;
    }

    public List<Task> getTasks() {
        return new ArrayList<>(repo.findAll());
    }

    public UUID convertStringToUUID(String id){
        return UUID.fromString(id);
    }

    public Task getTaskFromId(UUID id){
        List<Task> tasks = getTasks();
        for (Task task: tasks){
            if (task.getId().equals(id)){
                return task;
            }
        }
        throw new IllegalArgumentException("Id not found");
    }

    public void addTask(Task task){
        repo.save(task);
    }

    public void addSubTaskToTask(UUID uuid, SubTask subTask){
        Task task = getTaskFromId(uuid);
        task.addSubTask(subTask);
        repo.saveAndFlush(task);
    }

    public void editTask(Task task, String id){
        UUID uuid = UUID.fromString(id);
        Task correspondingTask = getTaskFromId(uuid);
        correspondingTask.setDate(task.getDate());
        correspondingTask.setTime(task.getTime());
        correspondingTask.setDescription(task.getDescription());
        correspondingTask.setName(task.getName());
        repo.save(correspondingTask);
    }
    public void saveAndFlushTaskToRepo(Task task){
        repo.saveAndFlush(task);
    }
}
