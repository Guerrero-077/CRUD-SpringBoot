package com.sena.TaskManagement.DTOs;

import java.time.LocalDate;

public class RequestRegisterTask {

    // ===================
    // = Attributes =
    // ===================

    private int id;
    private String name;
    private String description;
    private LocalDate creationDate;
    private LocalDate expirationDate;
    private boolean active;
    private Integer priorityId;
    private Integer taskStatusId;

    // ===========================
    // = Constructors =
    // ===========================

    public RequestRegisterTask() {
    }

    public RequestRegisterTask(int id, String name, String description, LocalDate creationDate,
            LocalDate expirationDate, boolean active, Integer priorityId, Integer taskStatusId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
        this.active = active;
        this.priorityId = priorityId;
        this.taskStatusId = taskStatusId;
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

    public void setPriorityId(Integer priorityId) {
        this.priorityId = priorityId;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(int priorityId) {
        this.priorityId = priorityId;
    }

    public Integer getTaskStatusId() {
        return taskStatusId;
    }

    public void setTaskStatusId(Integer taskStatusId) {
        this.taskStatusId = taskStatusId;
    }

    // ==========================
    // = Validation Helpers =
    // ==========================

    // Puedes agregar aquí algún método adicional para validar la fecha de
    // expiración y otras reglas de negocio
    public boolean isExpirationDateValid() {
        return expirationDate != null && !expirationDate.isBefore(LocalDate.now());
    }

}