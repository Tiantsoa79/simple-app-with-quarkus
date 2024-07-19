package org.acme.model;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Person extends PanacheEntity {
  public String name;
  public LocalDate birth;

  public Person() {
  }

  public Person(String name, LocalDate birth) {
    this.name = name;
    this.birth = birth;
  }

  // Return name as uppercase in the model
  public String getName() {
    return name.toUpperCase();
  }

  // Store all names in lowercase in the DB
  public void setName(String name) {
    this.name = name.toLowerCase();
  }

  public LocalDate getBirth() {
    return birth;
  }

  public void setBirth(LocalDate birth) {
    this.birth = birth;
  }
}
