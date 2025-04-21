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

import com.sena.TaskManagement.DTOs.RequestRegisterTasksTags;
import com.sena.TaskManagement.DTOs.responseDto;
import com.sena.TaskManagement.Service.TasksTagsServices;

@RestController
@RequestMapping("api/v1/tasks-tags")
public class TasksTagsController {

    @Autowired
    private TasksTagsServices tasksTagsServices;

    // Obtener todo
    @GetMapping
    public ResponseEntity<Object> findAll() {
        var ListCategory = tasksTagsServices.findAllTaskTags();
        return new ResponseEntity<Object>(ListCategory, HttpStatus.OK);
    }

    // // Obtener por nombre
    // @GetMapping("/filter/{name}")
    // public ResponseEntity<Object> findByNameCategory(@PathVariable String name) {
    // var ListCategory = tasksTagsServices.findByNameCategory(name);
    // return new ResponseEntity<Object>(ListCategory, HttpStatus.OK);
    // }

    // Obtener por Id
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id) {
        var category = tasksTagsServices.findByIdTaskTags(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    // Crear
    @PostMapping
    public ResponseEntity<responseDto> saveCategory(@RequestBody RequestRegisterTasksTags categoryDto) {
        responseDto response = tasksTagsServices.save(categoryDto);
        return new ResponseEntity<>(response, response.getStatus());
    }

    // Actualizar
    @PutMapping("/")
    public ResponseEntity<responseDto> putMethodName(@RequestBody RequestRegisterTasksTags category) {
        responseDto response = tasksTagsServices.update(category);
        return new ResponseEntity<>(response, response.getStatus());
    }

    // Borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<responseDto> deleteCategory(@PathVariable int id) {
        responseDto response = tasksTagsServices.delete(id);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
