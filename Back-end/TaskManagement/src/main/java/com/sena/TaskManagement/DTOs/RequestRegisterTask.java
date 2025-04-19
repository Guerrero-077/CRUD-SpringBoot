package com.sena.TaskManagement.DTOs;

import java.time.LocalDate;
import java.util.List;


public class RequestRegisterTask {

    /*
     * Agregar al DTO solo los elementos a exponer según
     * la petición o respuesta
     */

    // ===================
    // = Attributes =
    // ===================

    private String title;
    private String description;
    private LocalDate expirationDate;
    private boolean active = true;
    private List<Integer> categoryIds;
    private List<Integer> tagIds;
    private List<Integer> priorityIds;

    // ===========================
    // Constructors =
    // ===========================

    public RequestRegisterTask() {
    }

    public RequestRegisterTask(String title, String description, LocalDate expirationDate, boolean active,
            List<Integer> categoryIds, List<Integer> tagIds, List<Integer> priorityIds) {
        this.title = title;
        this.description = description;
        this.expirationDate = expirationDate;
        this.active = active;
        this.categoryIds = categoryIds;
        this.tagIds = tagIds;
        this.priorityIds = priorityIds;
    }

    // ==========================
    // = Getters and Setters =
    // ==========================

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

    public List<Integer> getPriorityIds() {
        return priorityIds;
    }

    public void setPriorityIds(List<Integer> priorityIds) {
        this.priorityIds = priorityIds;
    }

}
