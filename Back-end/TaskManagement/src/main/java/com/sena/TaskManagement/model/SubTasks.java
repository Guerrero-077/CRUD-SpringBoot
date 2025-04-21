package com.sena.TaskManagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "subtasks")
public class SubTasks {

    // ===================
    // = Attributes =
    // ===================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    // ===================
    // = Relaciones =
    // ===================

    @ManyToOne
    @JoinColumn(name = "task_id")
    // @JsonIgnoreProperties("subTasks")
    private Tasks tasksForSubTasks;

    // ===========================
    // Constructors =
    // ===========================

    public SubTasks() {
    }

    public SubTasks(int id, String name, Tasks tasksForSubTasks) {
        this.id = id;
        this.name = name;
        this.tasksForSubTasks = tasksForSubTasks;
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

    public Tasks getTasksForSubTasks() {
        return tasksForSubTasks;
    }

    public void setTasksForSubTasks(Tasks tasksForSubTasks) {
        this.tasksForSubTasks = tasksForSubTasks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
