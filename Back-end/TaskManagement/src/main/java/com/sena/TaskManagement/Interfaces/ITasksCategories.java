package com.sena.TaskManagement.Interfaces;


import org.springframework.data.jpa.repository.JpaRepository;
import com.sena.TaskManagement.model.TasksCategories;

public interface ITasksCategories extends JpaRepository<TasksCategories, Integer>{

    /*
     * JpaRepository incluye*SELECT
     *
     * UPDATE
     * INSERT
     * DELETE
     * por defecto
     */

}
