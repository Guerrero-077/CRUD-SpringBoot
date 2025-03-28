package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "reminders")
public class reminders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10)
    private int id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "idTask", length = 10)
    private int idTaks;

    public reminders() {
    }

    public reminders(int id, String name, int idTaks) {
        this.id = id;
        this.name = name;
        this.idTaks = idTaks;
    }

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

    public int getIdTaks() {
        return idTaks;
    }

    public void setIdTaks(int idTaks) {
        this.idTaks = idTaks;
    }

}
