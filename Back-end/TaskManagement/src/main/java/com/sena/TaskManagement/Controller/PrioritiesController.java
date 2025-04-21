package com.sena.TaskManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sena.TaskManagement.DTOs.RequestRegisterPriorities;
import com.sena.TaskManagement.DTOs.responseDto;
import com.sena.TaskManagement.Service.PrioritiesServices;

@RestController
@RequestMapping("api/v1/priorities")
public class PrioritiesController {

    @Autowired
    private PrioritiesServices prioritiesServices;

    /*
     * GET =consulta
     * POST= crear
     * PUT= actualizar
     * PATCH= actualizar parcial
     * DELETE= eliminar
     */
    @GetMapping
    public ResponseEntity<Object> findAll() {
        var ListCategory = prioritiesServices.findAll();
        return new ResponseEntity<Object>(ListCategory, HttpStatus.OK);
    }

    // // Obtener por nombre
    // @GetMapping("/filter/{name}")
    // public ResponseEntity<Object> findByNameCategory(@PathVariable String name) {
    // var ListCategory = prioritiesServices.findByNameCategory(name);
    // return new ResponseEntity<Object>(ListCategory, HttpStatus.OK);
    // }

    // Obtener por Id
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id) {
        var priorities = prioritiesServices.findById(id);
        return new ResponseEntity<>(priorities, HttpStatus.OK);
    }

    // Crear
    @PostMapping
    public ResponseEntity<responseDto> save(@RequestBody RequestRegisterPriorities prioritiesDto) {
        responseDto response = prioritiesServices.save(prioritiesDto);
        return new ResponseEntity<>(response, response.getStatus());
    }

    // Actualizar
    @PutMapping("/")
    public ResponseEntity<responseDto> update(@RequestBody RequestRegisterPriorities priorities) {
        responseDto response = prioritiesServices.update(priorities);
        return new ResponseEntity<>(response, response.getStatus());
    }

    // Borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<responseDto> delete(@PathVariable int id) {
        responseDto response = prioritiesServices.delete(id);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
