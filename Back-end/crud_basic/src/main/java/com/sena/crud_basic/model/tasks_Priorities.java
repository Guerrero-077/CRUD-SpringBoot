package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "tasks_Priorities")
public class tasks_Priorities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10)
    private int id;

    @Column(name = "idPriorities", length = 10)
    private int idPriorities;

    public tasks_Priorities() {
    }

    public tasks_Priorities(int id, int idPriorities) {
        this.id = id;
        this.idPriorities = idPriorities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPriorities() {
        return idPriorities;
    }

    public void setIdPriorities(int idPriorities) {
        this.idPriorities = idPriorities;
    }

}
