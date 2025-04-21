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

import com.sena.TaskManagement.DTOs.RequestRegisterTags;
import com.sena.TaskManagement.DTOs.responseDto;
import com.sena.TaskManagement.Service.TagsServices;

@RestController
@RequestMapping("api/v1/tags")
public class TagsController {

    @Autowired
    private TagsServices tagsServices;

    /*
     * GET =consulta
     * POST= crear
     * PUT= actualizar
     * PATCH= actualizar parcial
     * DELETE= eliminar
     */
    @GetMapping
    public ResponseEntity<Object> findAll() {
        var ListCategory = tagsServices.findAll();
        return new ResponseEntity<Object>(ListCategory, HttpStatus.OK);
    }

    // // Obtener por nombre
    // @GetMapping("/filter/{name}")
    // public ResponseEntity<Object> findByNameCategory(@PathVariable String name) {
    // var ListCategory = tagsServices.findByNameCategory(name);
    // return new ResponseEntity<Object>(ListCategory, HttpStatus.OK);
    // }

    // Obtener por Id
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id) {
        var tags = tagsServices.findById(id);
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    // Crear
    @PostMapping
    public ResponseEntity<responseDto> save(@RequestBody RequestRegisterTags tagsDto) {
        responseDto response = tagsServices.save(tagsDto);
        return new ResponseEntity<>(response, response.getStatus());
    }

    // Actualizar
    @PutMapping("/")
    public ResponseEntity<responseDto> update(@RequestBody RequestRegisterTags tags) {
        responseDto response = tagsServices.update(tags);
        return new ResponseEntity<>(response, response.getStatus());
    }

    // Borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<responseDto> delete(@PathVariable int id) {
        responseDto response = tagsServices.delete(id);
        return new ResponseEntity<>(response, response.getStatus());
    }

}
