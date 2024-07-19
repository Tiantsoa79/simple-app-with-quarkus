package org.acme.service;


import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.model.DTO.PersonDTO;
import org.acme.model.Person;
import org.acme.model.Pokemon;
import org.acme.rest.PokemonRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class PokemonService {
  @Inject
  @RestClient
  PokemonRestClient pokemonRestClient;

  public Uni<List<Pokemon>> getAllPokemon(){
    return Pokemon.listAll();
  }

  public Uni<Pokemon> addPokemon(String name) {
    Pokemon newPokemon = new Pokemon();
    newPokemon.setName(name);
    return newPokemon.persist().replaceWith(newPokemon);
  }

  @Transactional
  public Uni<Pokemon> savePokemonById(String id) {
    return pokemonRestClient.getPokemonById(Integer.parseInt(id))
        .onItem().transformToUni(response -> {
            return this.addPokemon(response.name);
         });
  }



//  public Uni<Pokemon> addPokemon(String name) {
//
//    return Pokemon.findByName(name)
//      .onItem().transformToUni(existingPokemon -> {
//        if (existingPokemon != null) {
//          // Retourner l'existant si trouvé
//          return Uni.createFrom().item(existingPokemon);
//        } else {
//          // Si pas trouvé, créer un nouveau Pokémon et le persister
//          Pokemon newPokemon = new Pokemon();
//          newPokemon.setName(name);
//          return newPokemon.persist().replaceWith(newPokemon);
//        }
//      });
//  }
}
