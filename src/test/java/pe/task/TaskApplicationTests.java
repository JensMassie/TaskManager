package pe.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pe.task.domain.Task;
import pe.task.domain.TaskRepository;
import pe.task.service.TaskService;
import pe.task.service.TaskServiceImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskApplicationTests {

    @Autowired
    TaskService service;

    @Test
    void contextLoads() {
    }

    //TaskService
    @Test
    void AddTask_AllValuesSet_addsTask(){
        List<Task> tasks = service.getTasks();

        int oSize = tasks.size();
        Task task = new Task();
        task.setName("TestTask");
        task.setTime(LocalTime.of(2,2));
        task.setDate(LocalDate.of(2000, 2, 6));
        task.setDescription("This is a test");
        service.addTask(task);

        tasks = service.getTasks();


        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        assertEquals(oSize + 1, tasks.size());
        Task taskTest = tasks.get(oSize);
        assertEquals(taskTest.getId(), task.getId());
    }

    @Test
    void getTaskFromId_returnsTask(){
        Task task = new Task();
        task.setName("TestTask");
        task.setTime(LocalTime.of(2,2));
        task.setDate(LocalDate.of(2000, 2, 6));
        task.setDescription("This is a test");
        service.addTask(task);

        UUID id = task.getId();
        Task newtask = service.getTaskFromId(id);
        assertEquals(task.getId(), newtask.getId());
    }

    //Task
    @Test
    void Task_EmptyTaskName_throwsError(){
        try {
            Task task = new Task();
            task.setName("");
            task.setTime(LocalTime.of(2, 2));
            task.setDate(LocalDate.of(2000, 2, 6));
            task.setDescription("This is a test");
            service.addTask(task);
            fail();
        }catch (Exception e){
            assertTrue(true);
        }
    }

    @Test
    void Task_DateFormatCorrect(){
        Task task = new Task();
        task.setName("TestTask");
        task.setTime(LocalTime.of(2,2));
        task.setDate(LocalDate.of(2000, 2, 6));
        task.setDescription("This is a test");
        assertEquals(task.getDate().toString(),"2000-02-06");
    }

    @Test
    void Task_TimeFormatCorrect(){
        Task task = new Task();
        task.setName("TestTask");
        task.setTime(LocalTime.of(2,2));
        task.setDate(LocalDate.of(2000, 2, 6));
        task.setDescription("This is a test");
        assertEquals(task.getTime().toString(),"02:02");
    }


}
