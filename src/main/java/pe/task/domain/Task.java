package pe.task.domain;


import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.rmi.server.UID;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Entity
public class Task {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private
    LocalDate date;
    @DateTimeFormat(pattern = "HH:mm")
    private
    LocalTime time;
    @NotEmpty
    private String name, description;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private
    UUID id;
    private ArrayList<SubTask> subTasks;

    public Task(){
        subTasks = new ArrayList<>();

    }

    public Task(String name, LocalTime time, LocalDate date, String description){
        this.id = UUID.randomUUID();
        this.name = name;
        this.date = date;
        this.time = time;
        this.description = description;
        subTasks = new ArrayList<>();
    }

    public ArrayList<SubTask> getSubTasks() {
        return subTasks;
    }

    public void addSubTask(SubTask subTask){
        //lelijke oplossing
        if (subTasks == null) {
            subTasks = new ArrayList<>();
        }
        else {
            subTasks = getSubTasks();
        }
        subTasks.add(subTask);
    }

    public String getDescription() {
        return description;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
