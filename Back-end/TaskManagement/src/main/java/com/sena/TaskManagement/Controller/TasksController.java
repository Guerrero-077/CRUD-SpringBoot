package com.sena.TaskManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.TaskManagement.DTOs.RequestRegisterTask;
import com.sena.TaskManagement.Service.TasksServices;
import com.sena.TaskManagement.model.Tasks;

@RestController
@RequestMapping("api/v1/tasks")
public class TasksController {

    @Autowired
    private TasksServices tasksServices;

    @GetMapping("/")
    public ResponseEntity<Object> findAll() {
        var listTasks = tasksServices.findAllTasks();
        return new ResponseEntity<>(listTasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id) {
        var task = tasksServices.findTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<Tasks> createTask(@RequestBody RequestRegisterTask request) {
        Tasks newTask = tasksServices.createTask(request);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody Tasks task) {
        tasksServices.updateTask(id, task);
        return "Update ok";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        tasksServices.deleteTask(id);
        return "Delete ok";
    }

    @GetMapping("/filter/{title}")
    public ResponseEntity<Object> filterForTitle(@PathVariable String title) {
        var listTasks = tasksServices.searchTasksByTitle(title);
        return new ResponseEntity<>(listTasks, HttpStatus.OK);
    }

}
