package com.sena.crud_basic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.service.tasksServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/v1/task")
public class tasksController {
    /*
     * GET = Obtener datos o constutar
     * POST = Crear un registro
     * PUT = Actualización total
     * PATCH = Actualización parcial
     * DELETE = Eliminar
     */

    @Autowired
    private tasksServices tasksServices;

    @GetMapping("/")
    public ResponseEntity<Object> findAllTareas() {
        var ListTasks = tasksServices.findAllTareas();
        return new ResponseEntity<Object>(ListTasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdTareas(@PathVariable int id) {
        var task = tasksServices.findByIdTareas(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

}
