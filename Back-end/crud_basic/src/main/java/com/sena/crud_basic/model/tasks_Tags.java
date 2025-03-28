package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "tasks_Tags")
public class tasks_Tags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10)
    private int id;

    @Column(name = "idTasg", length = 10)
    private int idTasg;

    public tasks_Tags() {
    }

    public tasks_Tags(int id, int idTasg) {
        this.id = id;
        this.idTasg = idTasg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTasg() {
        return idTasg;
    }

    public void setIdTasg(int idTasg) {
        this.idTasg = idTasg;
    }

}
