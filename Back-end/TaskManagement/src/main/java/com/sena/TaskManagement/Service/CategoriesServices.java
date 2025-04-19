package com.sena.TaskManagement.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.TaskManagement.DTOs.RequestRegisterCategories;
import com.sena.TaskManagement.Interfaces.ICategories;
import com.sena.TaskManagement.model.Categories;

@Service
public class CategoriesServices {
    /*
     * @Autowired = Incluye la conexión de la interface
     */

    @Autowired
    private ICategories categoriesData;

    public List<Categories> findAllTareas() {
        return categoriesData.findAll();
    }

    public Optional<Categories> findByIdTareas(int id) {
        return categoriesData.findById(id);
    }

    public void save(RequestRegisterCategories categories) {
        categoriesData.save(convertRegisterToTask(categories));
    }

    public Categories convertRegisterToTask(RequestRegisterCategories categories) {
        // Se pasa solo el nombre y una lista vacía de tasksCategories
        return new Categories(
                0,
                categories.getName(),
                new ArrayList<>() // Taks

        );
    }

    public Categories createCategories(RequestRegisterCategories request) {
        Categories categories = new Categories();
        categories.setName(request.getName());
        return categoriesData.save(categories);
    }

    public void update(int id, Categories categoriesUpdate) {
        var categories = findByIdTareas(id);
        if (categories.isPresent()) {
            categories.get().setName(categoriesUpdate.getName());
            categoriesData.save(categories.get());

        }
    }

    public void delete(int id) {
        var categories = findByIdTareas(id);
        if (categories.isPresent()) {// trae un boolean si existe o no
            categoriesData.delete(categories.get());
        }
    }

    // public List<Categories> filterForTitle(String title) {
    // return categoriesData.findByTitleContainingIgnoreCase(title);
    // }
}
