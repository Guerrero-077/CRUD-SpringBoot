package com.sena.TaskManagement.DTOs;

public class RequestRegisterReminders {
    // ===================
    // = Attributes =
    // ===================
    private int id;
    private String name;
    private int task_id;

    // ===========================
    // Constructors =
    // ===========================

    public RequestRegisterReminders() {
    }

    // ==========================
    // = Getters and Setters =
    // ==========================

    public RequestRegisterReminders(int id, String name, int task_id) {
        this.id = id;
        this.name = name;
        this.task_id = task_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
