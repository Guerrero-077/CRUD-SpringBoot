package com.sena.TaskManagement.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    // ===================
    // = Relaciones =
    // ===================

    @OneToMany(mappedBy = "categories")
    @JsonIgnoreProperties("categories")
    private List<TasksCategories> tasks = new ArrayList<>();

}
