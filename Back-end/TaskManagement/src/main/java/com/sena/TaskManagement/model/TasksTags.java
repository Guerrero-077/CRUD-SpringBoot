package com.sena.TaskManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "tasksTags")
public class TasksTags {

    // ===================
    // = Attributes =
    // ===================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // ===================
    // = Relaciones =
    // ===================

    @ManyToOne
    @JoinColumn(name = "task_id")
    @JsonIgnoreProperties({ "tasksTags" })
    private Tasks tasks;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    @JsonIgnoreProperties({ "tasksTags" })
    private Tags tags;

    // ===========================
    // Constructors =
    // ===========================

    public TasksTags() {
    }

    public TasksTags(int id, Tasks tasks, Tags tags) {
        this.id = id;
        this.tasks = tasks;
        this.tags = tags;
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

    public Tags getTags() {
        return tags;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }

}
