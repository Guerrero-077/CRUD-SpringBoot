package com.sena.TaskManagement.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @Column(name = "title")
    private String title;

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
    @OneToMany(mappedBy = "tasksForSubTasks")
    @JsonIgnoreProperties("tasksForSubTasks")
    private List<SubTasks> subTasks;

    // history
    @OneToMany(mappedBy = "taskForHistory")
    @JsonIgnoreProperties("taskForHistory")
    private List<History> history = new ArrayList<>();

    // tasksCategories
    @OneToMany(mappedBy = "tasks")
    @JsonIgnoreProperties("tasks")
    private List<TasksCategories> tasksCategories = new ArrayList<>();

    // tasksTags
    @OneToMany(mappedBy = "tasks")
    @JsonIgnoreProperties("tasks")
    private List<TasksTags> tasksTags = new ArrayList<>();

    // Reminders
    @OneToMany(mappedBy = "tasksForReminders")
    @JsonManagedReference
    private List<Reminders> reminders = new ArrayList<>();

    // ===================
    // = muchos a Uno =
    // ===================

    // taskStatus
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_status", nullable = false)
    private TaskStatus taskStatus; // Relación muchos a uno con TaskStatus

    // taskPriority
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_priority", nullable = false)
    private Priorities priority; // Relación muchos a uno con Priority
    

    // ===========================
    // Constructors =
    // ===========================

    public Tasks() {
    }

    public Tasks(int id, String title, String description, LocalDate creation_date, LocalDate expiration_date,
            boolean active, List<SubTasks> subTasks, List<History> history, List<TasksCategories> tasksCategories,
            List<TasksTags> tasksTags, List<Reminders> reminders) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creation_date = creation_date;
        this.expiration_date = expiration_date;
        this.active = active;
        this.subTasks = subTasks;
        this.history = history;
        this.tasksCategories = tasksCategories;
        this.tasksTags = tasksTags;
        this.reminders = reminders;
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

}
