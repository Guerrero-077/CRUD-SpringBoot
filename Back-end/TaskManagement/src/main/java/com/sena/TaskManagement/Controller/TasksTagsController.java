package com.sena.TaskManagement.Controller;

import java.util.List;
import java.util.Optional;

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
import com.sena.TaskManagement.Service.TasksTagsServices;
import com.sena.TaskManagement.model.TasksTags;

@RestController
@RequestMapping("api/v1/tasks-tags")
public class TasksTagsController {

    @Autowired
    private TasksTagsServices tasksTagsServices;

    // ===============================
    // Obtener todas las relaciones
    // ===============================
    @GetMapping("/")
    public ResponseEntity<List<TasksTags>> findAll() {
        var list = tasksTagsServices.findAllTaskTags();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // ===============================
    // Obtener una por ID
    // ===============================
    @GetMapping("/{id}")
    public ResponseEntity<Optional<TasksTags>> findById(@PathVariable int id) {
        var result = tasksTagsServices.findByIdTaskTags(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // ===============================
    // Crear nueva relación
    // ===============================
    @PostMapping("/crear")
    public ResponseEntity<TasksTags> create(@RequestBody RequestRegisterTasksTags request) {
        TasksTags created = tasksTagsServices.create(request);
        if (created != null) {
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Ej: si los IDs no existen
        }
    }

    // ===============================
    // Actualizar relación
    // ===============================
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody RequestRegisterTasksTags request) {
        tasksTagsServices.update(id, request);
        return new ResponseEntity<>("Update ok", HttpStatus.OK);
    }

    // ===============================
    // Eliminar relación
    // ===============================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        tasksTagsServices.delete(id);
        return new ResponseEntity<>("Delete ok", HttpStatus.OK);
    }
}
