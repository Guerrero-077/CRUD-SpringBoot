package com.sena.TaskManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.TaskManagement.DTOs.RequestRegisterSubTasks;
import com.sena.TaskManagement.Service.SubTasksServices;
import com.sena.TaskManagement.model.SubTasks;

@RestController
@RequestMapping("api/v1/subtasks")
public class SubTasksController {

    @Autowired
    private SubTasksServices subTasksServices;


     @GetMapping("/")
    public ResponseEntity<Object> findAllSubTasks() {
        var listTasks = subTasksServices.findAllSubTasks();
        return new ResponseEntity<>(listTasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdSubTasks(@PathVariable int id) {
        var subTasks = subTasksServices.findByIdSubTasks(id);
        return new ResponseEntity<>(subTasks, HttpStatus.OK);
    }

    // ======================
    // = Create SubTasks =
    // ======================
    @PostMapping("/crear")
    public ResponseEntity<SubTasks> createSubTasks(@RequestBody RequestRegisterSubTasks request) {
        SubTasks createSubTasks = subTasksServices.createSubTasks(request);
        return ResponseEntity.ok(createSubTasks);
    }

    // ======================
    // = Update SubTasks =
    // ======================
    @PutMapping("/{id}")
    public ResponseEntity<String> updateHistory(@PathVariable int id, @RequestBody SubTasks subTasksUpdate) {
        try {
            subTasksServices.update(id, subTasksUpdate);
            return ResponseEntity.ok("SubTasks updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to update SubTasks: " + e.getMessage());
        }
    }
}
