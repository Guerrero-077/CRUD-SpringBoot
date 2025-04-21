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
import com.sena.TaskManagement.DTOs.RequestRegisterSubTasks;
import com.sena.TaskManagement.DTOs.responseDto;
import com.sena.TaskManagement.Service.SubTasksServices;

@RestController
@RequestMapping("api/v1/subtasks")
public class SubTasksController {

    @Autowired
    private SubTasksServices subTasksServices;


 /*
     * GET =consulta
     * POST= crear
     * PUT= actualizar
     * PATCH= actualizar parcial
     * DELETE= eliminar
     */
    @GetMapping
    public ResponseEntity<Object> findAll() {
        var ListCategory = subTasksServices.findAll();
        return new ResponseEntity<Object>(ListCategory, HttpStatus.OK);
    }

    // // Obtener por nombre
    // @GetMapping("/filter/{name}")
    // public ResponseEntity<Object> findByNameCategory(@PathVariable String name) {
    // var ListCategory = subTasksServices.findByNameCategory(name);
    // return new ResponseEntity<Object>(ListCategory, HttpStatus.OK);
    // }

    // Obtener por Id
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id) {
        var subTasks = subTasksServices.findById(id);
        return new ResponseEntity<>(subTasks, HttpStatus.OK);
    }

    // Crear
    @PostMapping
    public ResponseEntity<responseDto> save(@RequestBody RequestRegisterSubTasks subTasksDto) {
        responseDto response = subTasksServices.save(subTasksDto);
        return new ResponseEntity<>(response, response.getStatus());
    }

    // Actualizar
    @PutMapping("/")
    public ResponseEntity<responseDto> update(@RequestBody RequestRegisterSubTasks subTasks) {
        responseDto response = subTasksServices.update(subTasks);
        return new ResponseEntity<>(response, response.getStatus());
    }

    // Borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<responseDto> delete(@PathVariable int id) {
        responseDto response = subTasksServices.delete(id);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
