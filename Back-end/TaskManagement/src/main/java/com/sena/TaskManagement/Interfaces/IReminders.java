package com.sena.TaskManagement.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.TaskManagement.model.Reminders;

public interface IReminders extends JpaRepository <Reminders, Integer> {

}
