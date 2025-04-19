package com.sena.TaskManagement.DTOs;

import java.time.LocalDate;

public class RequestRegisterHistory {

    // ===================
    // = Attributes =
    // ===================

    private int id;
    private String history_action;
    private LocalDate history_date;
    private int task_id;

    // ===========================
    // Constructors =
    // ===========================

    public RequestRegisterHistory() {
    }

    public RequestRegisterHistory(int id, String history_action, LocalDate history_date, int task_id) {
        this.id = id;
        this.history_action = history_action;
        this.history_date = history_date;
        this.task_id = task_id;
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

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }
}
