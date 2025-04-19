package com.sena.TaskManagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "reminders")
public class Reminders {

    // ===================
    // = Attributes =
    // ===================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    // ===================
    // = Relaciones =
    // ===================

    @ManyToOne
    @JoinColumn(name = "task_id")
    // @JsonIgnoreProperties("reminders")
    @JsonBackReference
    // @JsonIgnoreProperties({ "reminders", "subTasks", "history", "tasksCategories"
    // })

    private Tasks tasksForReminders;

    // ===========================
    // Constructors =
    // ===========================

    public Reminders() {
    }

    public Reminders(int id, String title, Tasks tasksForReminders) {
        this.id = id;
        this.title = title;
        this.tasksForReminders = tasksForReminders;
    }

    // ==========================
    // = Getters and Setters =
    // ==========================

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Tasks getTasksForReminders() {
        return tasksForReminders;
    }

    public void setTasksForReminders(Tasks tasksForReminders) {
        this.tasksForReminders = tasksForReminders;
    }

}
