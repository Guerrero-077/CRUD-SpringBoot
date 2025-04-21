package com.sena.TaskManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sena.TaskManagement.DTOs.RequestRegisterTasksCategories;
import com.sena.TaskManagement.DTOs.responseDto;
import com.sena.TaskManagement.Service.TaskCategoriesService;

@RestController
@RequestMapping("/api/v1/tasks-categories")
public class TasksCategoriesController {

    @Autowired
    private TaskCategoriesService taskCategoriesService;

    // ===============================
    // Obtener todas las relaciones
    // ===============================
    @GetMapping
    public ResponseEntity<List<RequestRegisterTasksCategories>> findAll() {
        List<RequestRegisterTasksCategories> list = taskCategoriesService.findAll();
        return ResponseEntity.ok(list);
    }

    // ===============================
    // Obtener una relación por ID
    // ===============================
    @GetMapping("/{id}")
    public ResponseEntity<RequestRegisterTasksCategories> findById(@PathVariable int id) {
        try {
            RequestRegisterTasksCategories result = taskCategoriesService.findById(id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // Devuelve una respuesta vacía en caso de error
        }
    }

    // ===============================
    // Crear una nueva relación
    // ===============================
    @PostMapping
    public ResponseEntity<responseDto> create(@RequestBody RequestRegisterTasksCategories request) {
        responseDto response = taskCategoriesService.save(request);
        return ResponseEntity.status(response.getStatus())
                .body(response);
    }

    // ===============================
    // Actualizar una relación existente
    // ===============================
    @PutMapping("/{id}")
    public ResponseEntity<responseDto> update(@PathVariable int id,
            @RequestBody RequestRegisterTasksCategories request) {
        request.setId(id); // Aseguramos que el ID sea el correcto
        responseDto response = taskCategoriesService.update(request);
        return ResponseEntity.status(response.getStatus())
                .body(response);
    }

    // ===============================
    // Eliminar una relación
    // ===============================
    @DeleteMapping("/{id}")
    public ResponseEntity<responseDto> delete(@PathVariable int id) {
        responseDto response = taskCategoriesService.delete(id);
        return ResponseEntity.status(response.getStatus())
                .body(response);
    }
}