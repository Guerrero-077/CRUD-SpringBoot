package com.sena.TaskManagement.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "tasks")
public class Tasks {

    // ===================
    // = Attributes =
    // ===================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "creation_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate creation_date;

    @Column(name = "expiration_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiration_date;

    @Column(name = "active")
    private boolean active;

    // ===================
    // = Relaciones =
    // ===================

    // ===================
    // = Uno a muchos =
    // ===================

    // subTasks
    @OneToMany(mappedBy = "tasksForSubTasks", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubTasks> subTasks = new ArrayList<>();

    // history
    @OneToMany(mappedBy = "taskForHistory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<History> history = new ArrayList<>();

    // tasksCategories
    @OneToMany(mappedBy = "tasks", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TasksCategories> tasksCategories = new ArrayList<>();

    // tasksTags
    @OneToMany(mappedBy = "tasks", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TasksTags> tasksTags = new ArrayList<>();

    // Reminders
    @OneToMany(mappedBy = "tasksForReminders", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reminders> reminders = new ArrayList<>();

    // ===================
    // = Muchos a Uno =
    // ===================

    // taskStatus
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_status")
    private TaskStatus taskStatus; // Relación muchos a uno con TaskStatus

    // taskPriority
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_priority")
    private Priorities priority; // Relación muchos a uno con Priority

    // ===========================
    // Constructors =
    // ===========================

    public Tasks() {
    }

    public Tasks(int id, String name, String description, LocalDate creation_date, LocalDate expiration_date,
            boolean active, List<SubTasks> subTasks, List<History> history, List<TasksCategories> tasksCategories,
            List<TasksTags> tasksTags, List<Reminders> reminders, TaskStatus taskStatus, Priorities priority) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creation_date = creation_date;
        this.expiration_date = expiration_date;
        this.active = active;
        this.subTasks = subTasks;
        this.history = history;
        this.tasksCategories = tasksCategories;
        this.tasksTags = tasksTags;
        this.reminders = reminders;
        this.taskStatus = taskStatus;
        this.priority = priority;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDate creation_date) {
        this.creation_date = creation_date;
    }

    public LocalDate getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(LocalDate expiration_date) {
        this.expiration_date = expiration_date;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<SubTasks> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTasks> subTasks) {
        this.subTasks = subTasks;
    }

    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }

    public List<TasksCategories> getTasksCategories() {
        return tasksCategories;
    }

    public void setTasksCategories(List<TasksCategories> tasksCategories) {
        this.tasksCategories = tasksCategories;
    }

    public List<TasksTags> getTasksTags() {
        return tasksTags;
    }

    public void setTasksTags(List<TasksTags> tasksTags) {
        this.tasksTags = tasksTags;
    }

    public List<Reminders> getReminders() {
        return reminders;
    }

    public void setReminders(List<Reminders> reminders) {
        this.reminders = reminders;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Priorities getPriority() {
        return priority;
    }

    public void setPriority(Priorities priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
