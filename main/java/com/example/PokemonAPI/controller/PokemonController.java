package com.example.PokemonAPI.controller;

import com.example.PokemonAPI.model.Pokemon;
import com.example.PokemonAPI.service.PokemonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokemones")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping
    public List<Pokemon> obtenerTodos(){
        return pokemonService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Pokemon obtenerPorId(@PathVariable Long id){
        return pokemonService.obtenerPorId(id);
    }

    @PostMapping
    public Pokemon crearPokemon(@Valid @RequestBody Pokemon pokemon){

        return pokemonService.crearPokemon(pokemon);
    }

    @PutMapping("/{id}")
    public Pokemon editarPokemon(@PathVariable Long id,@Valid @RequestBody Pokemon pokemon){
        return pokemonService.actualizarPokemonCompleto(id, pokemon);
    }

    @PatchMapping("/{id}")
    public Pokemon actualizarParcial(@PathVariable Long id,@Valid @RequestBody Pokemon pokemon){
        return pokemonService.actualizarPokemonParcial(id, pokemon);
    }

    @DeleteMapping("/{id}")
    public void eliminarPokemon(@PathVariable Long id){
        pokemonService.eliminarPokemon(id);
    }


}
