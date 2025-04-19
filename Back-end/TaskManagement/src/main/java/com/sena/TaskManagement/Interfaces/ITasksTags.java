package com.sena.TaskManagement.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.TaskManagement.model.TasksTags;


public interface ITasksTags extends JpaRepository<TasksTags, Integer> {

}
