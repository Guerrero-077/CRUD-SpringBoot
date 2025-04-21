package com.sena.TaskManagement.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "priorities")
public class Priorities {

    // ===================
    // = Attributes =
    // ===================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    // ===================
    // = Relaciones =
    // ===================
    @OneToMany(mappedBy = "priority", cascade = CascadeType.ALL, orphanRemoval = true)
    // @JsonManagedReference
    private List<Tasks> tasks;
    // ===========================
    // Constructors =
    // ===========================

    public Priorities() {
    }

    public Priorities(int id, String name) {
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
