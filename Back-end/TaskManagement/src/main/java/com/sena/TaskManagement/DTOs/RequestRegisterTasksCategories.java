package com.sena.TaskManagement.DTOs;

public class RequestRegisterTasksCategories {

    /*
     * Agregar al DTO solo los elementos a exponer según
     * la petición o respuesta
     */

    // ===================
    // = Attributes =
    // ===================

    private int id;
    private int taskId;
    private int categoriesId;

    // ===========================
    // Constructors =
    // ===========================

    public RequestRegisterTasksCategories() {
    }

    public RequestRegisterTasksCategories(int id, int taskId, int categoriesId) {
        this.id = id;
        this.taskId = taskId;
        this.categoriesId = categoriesId;
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

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(int categoriesId) {
        this.categoriesId = categoriesId;
    }

}
