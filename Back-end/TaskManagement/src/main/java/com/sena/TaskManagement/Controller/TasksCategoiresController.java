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

import com.sena.TaskManagement.DTOs.RequestRegisterTasksCategories;
import com.sena.TaskManagement.Service.TaskCategoriesServices;
import com.sena.TaskManagement.model.TasksCategories;


@RestController
@RequestMapping("api/v1/tasks-categories")
public class TasksCategoiresController {
    @Autowired
    private TaskCategoriesServices taskCategoriesServices;

    // ===============================
    // Obtener todas las relaciones
    // ===============================
    @GetMapping("/")
    public ResponseEntity<List<TasksCategories>> findAll() {
        var list = taskCategoriesServices.findAllTaskCategories();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // ===============================
    // Obtener una por ID
    // ===============================
    @GetMapping("/{id}")
    public ResponseEntity<Optional<TasksCategories>> findById(@PathVariable int id) {
        var result = taskCategoriesServices.findByIdTaskCategories(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // ===============================
    // Crear nueva relación
    // ===============================
    @PostMapping("/crear")
    public ResponseEntity<TasksCategories> create(@RequestBody RequestRegisterTasksCategories request) {
        TasksCategories created = taskCategoriesServices.create(request);
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
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody RequestRegisterTasksCategories request) {
        taskCategoriesServices.update(id, request);
        return new ResponseEntity<>("Update ok", HttpStatus.OK);
    }

    // ===============================
    // Eliminar relación
    // ===============================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        taskCategoriesServices.delete(id);
        return new ResponseEntity<>("Delete ok", HttpStatus.OK);
    }
}
