package com.sena.TaskManagement.DTOs;

public class RequestRegisterReminders {
    // ===================
    // = Attributes =
    // ===================
    private int id;
    private String title;
    private int task_id;

    // ===========================
    // Constructors =
    // ===========================

    public RequestRegisterReminders() {
    }

    public RequestRegisterReminders(int id, String title, int task_id) {
        this.id = id;
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

}
