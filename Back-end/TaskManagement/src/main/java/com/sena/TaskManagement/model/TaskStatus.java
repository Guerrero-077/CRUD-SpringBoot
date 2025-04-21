package com.sena.TaskManagement.model;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "TaskStatus")
public class TaskStatus {

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

    @OneToMany(mappedBy = "taskStatus", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Tasks> tasks;

    // ===========================
    // Constructors =
    // ===========================

    public TaskStatus() {
    }

    public TaskStatus(int id, String name, List<Tasks> tasks) {
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

    public List<Tasks> getTasks() {
        return tasks;
    }

    public void setTasks(List<Tasks> tasks) {
        this.tasks = tasks;
    }
}
