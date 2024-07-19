package org.acme.rest;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "pokemon-api")
@Path("/pokemon-species")
public interface PokemonRestClient {

  @GET
  @Path("/{id}")
  Uni<PokemonResponse> getPokemonById(@PathParam("id") int id);

    class PokemonResponse{
      public String id;
      public String name;
    }

}