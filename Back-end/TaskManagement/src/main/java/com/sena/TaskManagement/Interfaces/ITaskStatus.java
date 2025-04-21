package com.sena.TaskManagement.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.TaskManagement.model.TaskStatus;

public interface ITaskStatus extends JpaRepository <TaskStatus, Integer>{

}
