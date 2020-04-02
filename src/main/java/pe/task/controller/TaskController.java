package pe.task.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.task.domain.SubTask;
import pe.task.domain.Task;
import pe.task.domain.TaskRepository;
import pe.task.service.TaskService;
import pe.task.service.TaskServiceImpl;

import java.util.UUID;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository repository;


    public String indexnav(){
        return "index.html";
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("task", taskService.getTasks());
        return "task.html";
    }

    @RequestMapping("/edit/{id}")
    public String editTask(@PathVariable("id") String id, Model model){
        try {
            UUID uuid = UUID.fromString(id);

            Task task = taskService.getTaskFromId(uuid);
            model.addAttribute("task", task);
            return "editTask.html";

        }catch (Exception e){
            model.addAttribute("error", "TaskNotFound");
            return "task.html";
        }
    }

    @RequestMapping("/{id}/sub/create")
    public String subTask(@PathVariable("id") String id, Model model){
        return "createSubTask.html";
    }
    @PostMapping("/{id}/sub/create")
    public String addSubTask(@PathVariable("id") String id, @ModelAttribute SubTask subTask, Model model){
        try {
            UUID uuid = UUID.fromString(id);
            System.out.println(uuid);
            taskService.addSubTaskToTask(uuid, subTask);
        }catch (Exception e){
            model.addAttribute("error", "TaskNotFound");
            return "task.html";
        }
        return "redirect:/tasks";
    }

    @PostMapping("/edit/{id}")
    public String edit(@ModelAttribute Task task, @PathVariable String id, Model model) {
        System.out.println(id);
        try{
            UUID uuid = UUID.fromString(id);
            Task correspondingTask = taskService.getTaskFromId(uuid);
            correspondingTask.setDate(task.getDate());
            correspondingTask.setTime(task.getTime());
            correspondingTask.setDescription(task.getDescription());
            correspondingTask.setName(task.getName());
            repository.save(correspondingTask);

        }catch (Exception e){return "redirect:/tasks";}
        model.addAttribute("tasks", repository.findAll());

        return "redirect:/tasks";
    }

    @RequestMapping("/new")
    public String createTask(){
        return "createTask.html";
    }

    @PostMapping("/new")
    public String form(@ModelAttribute Task task){
        repository.saveAndFlush(task);
        return "redirect:/tasks";
    }

    @RequestMapping(value = "/{id}")
    public String getId(@PathVariable String id, Model model) {
        try {
            UUID uuid = UUID.fromString(id);
            Task task = taskService.getTaskFromId(uuid);
            model.addAttribute("obj", task);
            model.addAttribute("subtasks", task.getSubTasks());
            return "taskInfo.html";

        }catch (Exception e){
            model.addAttribute("error", "TaskNotFound");
            return "task.html";
        }
    }



}
