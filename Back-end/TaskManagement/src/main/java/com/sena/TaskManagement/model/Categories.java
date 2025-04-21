package com.sena.TaskManagement.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "categories")
public class Categories {

    // ===================
    // = Attributes =
    // ===================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    // ===================
    // = Relaciones =
    // ===================

    @OneToMany(mappedBy = "categories")
    private List<TasksCategories> tasks = new ArrayList<>();

    // ===========================
    // Constructors =
    // ===========================

    public Categories() {
    }

    public Categories(int id, String name, List<TasksCategories> tasks) {
        this.id = id;
        this.name = name;
        this.tasks = tasks;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TasksCategories> getTasks() {
        return tasks;
    }

    public void setTasks(List<TasksCategories> tasks) {
        this.tasks = tasks;
    }
}
