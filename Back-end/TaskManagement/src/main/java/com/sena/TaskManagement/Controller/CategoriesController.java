package com.sena.TaskManagement.Controller;

import com.sena.TaskManagement.DTOs.RequestRegisterCategories;
import com.sena.TaskManagement.DTOs.responseDto;
import com.sena.TaskManagement.Service.CategoriesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/categories")
public class CategoriesController {

    /*
     * GET =consulta
     * POST= crear
     * PUT= actualizar
     * PATCH= actualizar parcial
     * DELETE= eliminar
     */
    @Autowired
    private CategoriesServices _categoryService; // Obteber todos los categorias

    // Obtener todo
    @GetMapping
    public ResponseEntity<Object> findAllCategory() {
        var ListCategory = _categoryService.findAllCategory();
        return new ResponseEntity<Object>(ListCategory, HttpStatus.OK);
    }

    // // Obtener por nombre
    // @GetMapping("/filter/{name}")
    // public ResponseEntity<Object> findByNameCategory(@PathVariable String name) {
    //     var ListCategory = _categoryService.findByNameCategory(name);
    //     return new ResponseEntity<Object>(ListCategory, HttpStatus.OK);
    // }

    // Obtener por Id
    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdCategory(@PathVariable int id) {
        var category = _categoryService.findByIdCategories(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    // Crear
    @PostMapping
    public ResponseEntity<responseDto> saveCategory(@RequestBody RequestRegisterCategories categoryDto) {
        responseDto response = _categoryService.save(categoryDto);
        return new ResponseEntity<>(response, response.getStatus());
    }

    // Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<responseDto> putMethodName(@PathVariable int id,@RequestBody RequestRegisterCategories category) {
        responseDto response = _categoryService.update(id, category);
        return new ResponseEntity<>(response, response.getStatus());
    }

    // Borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<responseDto> deleteCategory(@PathVariable int id) {
        responseDto response = _categoryService.delete(id);
        return new ResponseEntity<>(response, response.getStatus());
    }

}
