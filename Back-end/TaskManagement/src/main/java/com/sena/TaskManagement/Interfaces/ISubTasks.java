package com.sena.TaskManagement.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.TaskManagement.model.SubTasks;



public interface ISubTasks  extends JpaRepository<SubTasks, Integer> {

}
