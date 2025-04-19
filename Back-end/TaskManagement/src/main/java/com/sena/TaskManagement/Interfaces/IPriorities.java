package com.sena.TaskManagement.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.TaskManagement.model.Priorities;


public interface IPriorities extends JpaRepository<Priorities, Integer>  {

}
