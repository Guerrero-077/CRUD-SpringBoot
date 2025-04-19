package com.sena.TaskManagement.Interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.TaskManagement.model.Tasks;

public interface ITasks extends JpaRepository<Tasks, Integer> {

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

    List<Tasks> findByTitleContainingIgnoreCase(String title);

}
