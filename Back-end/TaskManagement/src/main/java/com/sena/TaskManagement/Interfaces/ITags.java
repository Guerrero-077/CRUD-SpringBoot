package com.sena.TaskManagement.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.TaskManagement.model.Tags;


public interface ITags extends JpaRepository<Tags, Integer> {

}
