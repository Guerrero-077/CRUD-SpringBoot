package com.sena.crud_basic.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/*
 * Anotación been, indica que la clase es una entidad
 */
@Entity(name = "tasks")
public class tasks {

  // @Id=indica que es la primary key
  @Id
  // El valor sea es autoGenerado e autoincremental
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  // @column=indica que es una columna en la tabla
  /*
   * name=nombre de la columna en base de datos
   * lenght=longitud del campo
   * nullables= si acepta nulo o no
   */

  @Column(name = "id", length = 10)
  private int id;

  @Column(name = "title", length = 100)
  private String title;

  @Column(name = "description", length = 200)
  private String description;

  @Column(name = "creation_date")
  private LocalDateTime creation_date;

  @Column(name = "expiration_date")
  private LocalDateTime expiration_date;

  @Column(name = "active")
  private boolean active;

  public tasks() {
  }

  public tasks(int id, String title, String description, LocalDateTime creation_date, LocalDateTime expiration_date,
      boolean active) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.creation_date = creation_date;
    this.expiration_date = expiration_date;
    this.active = active;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

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

  public LocalDateTime getCreation_date() {
    return creation_date;
  }

  public void setCreation_date(LocalDateTime creation_date) {
    this.creation_date = creation_date;
  }

  public LocalDateTime getExpiration_date() {
    return expiration_date;
  }

  public void setExpiration_date(LocalDateTime expiration_date) {
    this.expiration_date = expiration_date;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

}
