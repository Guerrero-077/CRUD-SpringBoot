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

import com.sena.TaskManagement.DTOs.RequestRegisterTags;
import com.sena.TaskManagement.Service.TagsServices;
import com.sena.TaskManagement.model.Tags;

@RestController
@RequestMapping("api/v1/tags")
public class TagsController {

    @Autowired
    private TagsServices tagsServices;

    @GetMapping("/")
    public ResponseEntity<Object> findAllTags() {
        var listTasks = tagsServices.findAllTags();
        return new ResponseEntity<>(listTasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdTags(@PathVariable int id) {
        var tags = tagsServices.findByIdTags(id);
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody Tags tags) {
        tagsServices.update(id, tags);
        return "Update ok";
    }

    @PostMapping("/crear")
    public ResponseEntity<Tags> createTags(@RequestBody RequestRegisterTags request) {
        Tags newCategorie = tagsServices.createTags(request);
        return new ResponseEntity<>(newCategorie, HttpStatus.CREATED);
    }

}
