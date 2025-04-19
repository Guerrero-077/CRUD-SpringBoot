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

import com.sena.TaskManagement.DTOs.RequestRegisterCategories;
import com.sena.TaskManagement.Service.CategoriesServices;
import com.sena.TaskManagement.model.Categories;

@RestController
@RequestMapping("api/v1/categories")
public class CategoriesController {

    @Autowired
    private CategoriesServices categoriesService;

    @GetMapping("/")
    public ResponseEntity<Object> findAllTareas() {
        var listTasks = categoriesService.findAllTareas();
        return new ResponseEntity<>(listTasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdTareas(@PathVariable int id) {
        var categorie = categoriesService.findByIdTareas(id);
        return new ResponseEntity<>(categorie, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody Categories categorie) {
        categoriesService.update(id, categorie);
        return "Update ok";
    }

    @PostMapping("/crear")
    public ResponseEntity<Categories> createCategories(@RequestBody RequestRegisterCategories request) {
        Categories newCategorie = categoriesService.createCategories(request);
        return new ResponseEntity<>(newCategorie, HttpStatus.CREATED);
    }

    // @GetMapping("/filter/{title}")
    // public ResponseEntity<Object> filterForTitle(@PathVariable String title) {
    // var listTasks = categoriesService.filterForTitle(title);
    // return new ResponseEntity<>(listTasks, HttpStatus.OK);
    // }
}
