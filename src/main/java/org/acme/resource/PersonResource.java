package org.acme.resource;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.acme.model.DTO.PersonDTO;
import org.acme.model.Person;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
@Path("/people")
public class PersonResource {


  @GET
  @Path("/all")
  public Uni<List<Person>> get() {
    return Person.listAll();

  }

  @POST
  @Path("/new")
  public Uni<List<Person>> add(PersonDTO personDTO) {

    Person newPerson = new Person();
    newPerson.setName(personDTO.getName());
    newPerson.setBirth(personDTO.getBirth());

    return newPerson.persist().replaceWith(Person.listAll());
  }

}
