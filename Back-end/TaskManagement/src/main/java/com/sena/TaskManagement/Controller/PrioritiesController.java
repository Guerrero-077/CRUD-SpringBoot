package com.sena.TaskManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.TaskManagement.DTOs.RequestRegisterPriorities;
import com.sena.TaskManagement.Service.PrioritiesServices;
import com.sena.TaskManagement.model.Priorities;

@RestController
@RequestMapping("api/v1/priorities")
public class PrioritiesController {

    @Autowired
    private PrioritiesServices prioritiesServices;

        @GetMapping("/")
    public ResponseEntity<Object> findAllPriorities() {
        var listTasks = prioritiesServices.findAllPriorities();
        return new ResponseEntity<>(listTasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdPriorities(@PathVariable int id) {
        var Priorities = prioritiesServices.findByIdPriorities(id);
        return new ResponseEntity<>(Priorities, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody Priorities Priorities) {
        prioritiesServices.update(id, Priorities);
        return "Update ok";
    }

    @PostMapping("/crear")
    public ResponseEntity<Priorities> createTags(@RequestBody RequestRegisterPriorities request) {
        Priorities newCategorie = prioritiesServices.createPriorities(request);
        return new ResponseEntity<>(newCategorie, HttpStatus.CREATED);
    }
}
