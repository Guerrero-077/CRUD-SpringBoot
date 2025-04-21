package com.sena.TaskManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sena.TaskManagement.DTOs.Registrar;
import com.sena.TaskManagement.DTOs.TaskWithDetails;
import com.sena.TaskManagement.DTOs.responseDto;
import com.sena.TaskManagement.Service.TasksServices;

@RestController
@RequestMapping("/api/v1/tasks")
public class TasksController {

    @Autowired
    private TasksServices tasksServices;

    @GetMapping("/with-details")
    public List<TaskWithDetails> getTasksWithDetails() {
        return tasksServices.getTasksWithDetails();
    }

    @GetMapping("/filter/{id}")
    public List<TaskWithDetails> getFilter(@PathVariable int id) {
        return tasksServices.getFilter(id);
    }

    // Obtener todo
    @GetMapping
    public ResponseEntity<Object> findAllCategory() {
        var ListCategory = tasksServices.findAll();
        return new ResponseEntity<Object>(ListCategory, HttpStatus.OK);
    }

    // // Obtener por nombre
    // @GetMapping("/filter/{name}")
    // public ResponseEntity<Object> findByNameCategory(@PathVariable String name) {
    // var ListCategory = tasksServices.findByNameCategory(name);
    // return new ResponseEntity<Object>(ListCategory, HttpStatus.OK);
    // }

    // Obtener por Id
    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdCategory(@PathVariable int id) {
        var category = tasksServices.findById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    // Crear
    @PostMapping
    public ResponseEntity<responseDto> saveCategory(@RequestBody Registrar categoryDto) {
        responseDto response = tasksServices.save(categoryDto);
        return new ResponseEntity<>(response, response.getStatus());
    }

    // Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<responseDto> putMethodName(@PathVariable int id, @RequestBody Registrar category) {
        responseDto response = tasksServices.update(id, category);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PutMapping("/toggle-active/{id}")
    public ResponseEntity<responseDto> toggleActive(@PathVariable int id, @RequestParam boolean active) {
        responseDto response = tasksServices.toggleActive(id, active);
        return new ResponseEntity<>(response, response.getStatus());
    }

    // Borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<responseDto> deleteCategory(@PathVariable int id) {
        responseDto response = tasksServices.delete(id);
        return new ResponseEntity<>(response, response.getStatus());
    }

}