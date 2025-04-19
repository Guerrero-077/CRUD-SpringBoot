package com.sena.TaskManagement.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.TaskManagement.model.History;

public interface IHistory  extends JpaRepository<History, Integer>{

}
