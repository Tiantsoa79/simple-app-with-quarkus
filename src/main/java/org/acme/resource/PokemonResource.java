package org.acme.resource;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.acme.model.Pokemon;
import org.acme.service.PokemonService;

import java.util.List;

@Path("/pokemon")
public class PokemonResource {
  @Inject
  PokemonService pokemonService;

  @GET
  @Path("/all")
  public Uni<List<Pokemon>> get() {
    return pokemonService.getAllPokemon();

  }
  @GET
  @Path("/{id}")
  public Uni<Pokemon> getPokemonById(@PathParam("id") String id){
      return pokemonService.savePokemonById(id);
  }

}
