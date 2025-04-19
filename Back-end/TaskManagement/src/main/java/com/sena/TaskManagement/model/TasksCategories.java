package com.sena.TaskManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "tasksCategories")
public class TasksCategories {

    // ===================
    // = Attributes =
    // ===================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // ===========================
    // Constructors =
    // ===========================

    public TasksCategories() {
    }

    public TasksCategories(int id, Tasks tasks, Categories categories) {
        this.id = id;
        this.tasks = tasks;
        this.categories = categories;
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

    public Tasks getTasks() {
        return tasks;
    }

    public void setTasks(Tasks tasks) {
        this.tasks = tasks;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    // ===================
    // = Relaciones =
    // ===================

    @ManyToOne
    @JoinColumn(name = "task_id")
    @JsonIgnoreProperties("tasksCategories")
    private Tasks tasks;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("tasks")
    private Categories categories;

}
