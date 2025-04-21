package com.sena.TaskManagement.Controller;

import java.util.List;

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

import com.sena.TaskManagement.DTOs.HitoryWhitDetail;
import com.sena.TaskManagement.DTOs.RequestRegisterHistory;
import com.sena.TaskManagement.DTOs.responseDto;
import com.sena.TaskManagement.Service.HistoryServices;

@RestController
@RequestMapping("api/v1/history")
public class HistoryController {

    @Autowired
    private HistoryServices historyServices;

    /*
     * GET =consulta
     * POST= crear
     * PUT= actualizar
     * PATCH= actualizar parcial
     * DELETE= eliminar
     */

    @GetMapping("/with-details")
    public List<HitoryWhitDetail> getTasksWithDetails() {
        return historyServices.getHistHitoryWhitDetails();
    }

    @GetMapping
    public ResponseEntity<Object> findAllCategory() {
        var ListCategory = historyServices.findAll();
        return new ResponseEntity<Object>(ListCategory, HttpStatus.OK);
    }

    // // Obtener por nombre
    // @GetMapping("/filter/{name}")
    // public ResponseEntity<Object> findByNameCategory(@PathVariable String name) {
    // var ListCategory = historyServices.findByNameCategory(name);
    // return new ResponseEntity<Object>(ListCategory, HttpStatus.OK);
    // }

    // Obtener por Id
    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdCategory(@PathVariable int id) {
        var history = historyServices.findById(id);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }

    // Crear
    @PostMapping
    public ResponseEntity<responseDto> saveCategory(@RequestBody RequestRegisterHistory historyDto) {
        responseDto response = historyServices.save(historyDto);
        return new ResponseEntity<>(response, response.getStatus());
    }

    // Actualizar
    @PutMapping("/")
    public ResponseEntity<responseDto> putMethodName(@RequestBody RequestRegisterHistory history) {
        responseDto response = historyServices.update(history);
        return new ResponseEntity<>(response, response.getStatus());
    }

    // Borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<responseDto> deleteCategory(@PathVariable int id) {
        responseDto response = historyServices.delete(id);
        return new ResponseEntity<>(response, response.getStatus());
    }

}
