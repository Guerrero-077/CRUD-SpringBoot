package com.sena.TaskManagement.DTOs;

public class RequestRegisterPriorities {

    /*
     * Agregar al DTO solo los elementos a exponer según
     * la petición o respuesta
     */

    // ===================
    // = Attributes =
    // ===================

    private int id;
    private String name;

    // ===========================
    // Constructors =
    // ===========================

    public RequestRegisterPriorities() {
    }

    public RequestRegisterPriorities(int id, String name) {
        this.id = id;
        this.name = name;
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

}
