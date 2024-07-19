package org.acme.service;


import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.acme.model.Pokemon;
import org.acme.rest.PokemonRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;


@ApplicationScoped
public class PokemonService {

  @RestClient
  PokemonRestClient pokemonRestClient;

  public Uni<List<Pokemon>> getAllPokemon(){
    return Pokemon.listAll();
  }


  @Transactional
  public Uni<Pokemon> getPokemonById(String id) {
    return pokemonRestClient.getPokemonById(Integer.parseInt(id))
      .onItem().transformToUni(model -> {
        //  check if a Pokemon exists in database
        return Pokemon.existsByName(model.name)
          .onItem().transformToUni(exists -> {
            if (!exists) {
              // create and persist if not exists
              Pokemon pokemon = new Pokemon();
              pokemon.setName(model.name);
              return Panache.withTransaction(pokemon::persist).replaceWith(pokemon);
            } else {
              // retrieve if exist
              return Pokemon.find("name", model.name).firstResult();
            }
          });
      });
  }


}
