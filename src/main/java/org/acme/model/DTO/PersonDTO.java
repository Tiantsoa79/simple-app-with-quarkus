package org.acme.model.DTO;

import java.time.LocalDate;

public class PersonDTO {
  private String name;
  private LocalDate birth;

  public PersonDTO() {
  }

  public PersonDTO(String name, LocalDate birth) {
    this.name = name;
    this.birth = birth;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getBirth() {
    return birth;
  }

  public void setBirth(LocalDate birth) {
    this.birth = birth;
  }
}
