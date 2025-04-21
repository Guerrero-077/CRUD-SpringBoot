package com.sena.TaskManagement.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "history")
public class History {

    // ===================
    // = Attributes =
    // ===================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "history_action")
    private String history_action;

    @Column(name = "history_date")
    private LocalDate history_date;

    // ===================
    // = Relaciones =
    // ===================

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Tasks taskForHistory;

    // ===========================
    // Constructors =
    // ===========================

    public History() {
    }

    public History(int id, String history_action, LocalDate history_date, Tasks taskForHistory) {
        this.id = id;
        this.history_action = history_action;
        this.history_date = history_date;
        this.taskForHistory = taskForHistory;
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

    public String getHistory_action() {
        return history_action;
    }

    public void setHistory_action(String history_action) {
        this.history_action = history_action;
    }

    public LocalDate getHistory_date() {
        return history_date;
    }

    public void setHistory_date(LocalDate history_date) {
        this.history_date = history_date;
    }

    public Tasks getTaskForHistory() {
        return taskForHistory;
    }

    public void setTaskForHistory(Tasks taskForHistory) {
        this.taskForHistory = taskForHistory;
    }
}
