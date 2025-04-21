package com.sena.TaskManagement.DTOs;

import java.time.LocalDate;
import java.util.List;

public class Registrar {
    private String name;
    private String description;
    private LocalDate expirationDate;
    private boolean active;
    private Integer priorityId;
    private Integer taskStatusId;
    private List<Integer> categoryIds;
    private List<Integer> tagIds;
    private List<Integer> subTasksIds; // renombrado por convención Java
    private List<RequestRegisterSubTasks> subTasks;
    private List<RequestRegisterReminders> reminders;

    public Registrar() {
    }

    public Registrar(String name, String description, LocalDate expirationDate, boolean active, Integer priorityId,
            Integer taskStatusId, List<Integer> categoryIds, List<Integer> tagIds, List<Integer> subTasksIds,
            List<RequestRegisterSubTasks> subTasks, List<RequestRegisterReminders> reminders) {
        this.name = name;
        this.description = description;
        this.expirationDate = expirationDate;
        this.active = active;
        this.priorityId = priorityId;
        this.taskStatusId = taskStatusId;
        this.categoryIds = categoryIds;
        this.tagIds = tagIds;
        this.subTasksIds = subTasksIds;
        this.subTasks = subTasks;
        this.reminders = reminders;
    }

    // Getters y setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Integer priorityId) {
        this.priorityId = priorityId;
    }

    public Integer getTaskStatusId() {
        return taskStatusId;
    }

    public void setTaskStatusId(Integer taskStatusId) {
        this.taskStatusId = taskStatusId;
    }

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public List<Integer> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Integer> tagIds) {
        this.tagIds = tagIds;
    }

    public List<Integer> getSubTasksIds() {
        return subTasksIds;
    }

    public void setSubTasksIds(List<Integer> subTasksIds) {
        this.subTasksIds = subTasksIds;
    }

    public List<RequestRegisterSubTasks> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<RequestRegisterSubTasks> subTasks) {
        this.subTasks = subTasks;
    }

    public List<RequestRegisterReminders> getReminders() {
        return reminders;
    }

    public void setReminders(List<RequestRegisterReminders> reminders) {
        this.reminders = reminders;
    }
}
