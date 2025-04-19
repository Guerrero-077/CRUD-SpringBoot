package com.sena.TaskManagement.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sena.TaskManagement.model.Categories;

public interface ICategories extends JpaRepository<Categories, Integer> {
    /*
     * JpaRepository incluye*SELECT
     *
     * UPDATE
     * INSERT
     * DELETE
     * por defecto
     */

    // @Query("SELECT t FROM Tasks t WHERE t.title LIKE %?1%")
    // List<Tasks> findByTitle(String title);

    // List<Tasks> findByTitleContainingIgnoreCase(String title);

}
