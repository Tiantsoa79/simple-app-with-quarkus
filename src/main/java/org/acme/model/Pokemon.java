package org.acme.model;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;

import io.smallrye.mutiny.Uni;
import jakarta.persistence.Entity;

@Entity
public class Pokemon extends PanacheEntity {
  public String name;

  public Pokemon() {
  }

  public Pokemon(String name) {
    this.name = name;
  }
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public static Uni<Boolean> existsByName(String name) {
    return find("name", name).firstResult()
      .onItem().ifNotNull().transform(entity -> true)
      .onItem().ifNull().continueWith(false);
  }

}
