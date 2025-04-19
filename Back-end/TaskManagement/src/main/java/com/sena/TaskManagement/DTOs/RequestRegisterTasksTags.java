package com.sena.TaskManagement.DTOs;

public class RequestRegisterTasksTags {

    /*
     * Agregar al DTO solo los elementos a exponer según
     * la petición o respuesta
     */

    // ===================
    // = Attributes =
    // ===================

    private int id;
    private int taskId;
    private int tagsId;

    // ===========================
    // Constructors =
    // ===========================

    public RequestRegisterTasksTags() {
    }

    public RequestRegisterTasksTags(int id, int taskId, int tagsId) {
        this.id = id;
        this.taskId = taskId;
        this.tagsId = tagsId;
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

    public int getTagsId() {
        return tagsId;
    }

    public void setTagsId(int tagsId) {
        this.tagsId = tagsId;
    }

}
