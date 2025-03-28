package com.sena.crud_basic.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "history")
public class history {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10)
    private int id;

    @Column(name = "idTaks", length = 10)
    private int idTaks;

    @Column(name = "action")
    private boolean action;

    @Column(name = "date")
    private LocalDateTime date;

    public history() {
    }

    public history(int id, int idTaks, boolean action, LocalDateTime date) {
        this.id = id;
        this.idTaks = idTaks;
        this.action = action;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTaks() {
        return idTaks;
    }

    public void setIdTaks(int idTaks) {
        this.idTaks = idTaks;
    }

    public boolean isAction() {
        return action;
    }

    public void setAction(boolean action) {
        this.action = action;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}
