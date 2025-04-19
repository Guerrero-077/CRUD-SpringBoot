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

@Entity(name = "tags")
public class Tags {

    // ===================
    // = Attributes =
    // ===================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    // ===========================
    // Constructors =
    // ===========================

    public Tags() {
    }

    public Tags(int id, String name, List<TasksTags> tasksTags) {
        this.id = id;
        this.name = name;
        this.tasksTags = tasksTags;
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

    public List<TasksTags> getTasksTags() {
        return tasksTags;
    }

    public void setTasksTags(List<TasksTags> tasksTags) {
        this.tasksTags = tasksTags;
    }


    // ===================
    // = Relaciones =
    // ===================


    @OneToMany(mappedBy = "tags")
    @JsonIgnoreProperties({"tags"})
    private List<TasksTags> tasksTags = new ArrayList<>();


}
