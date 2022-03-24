package com.backend.cinejungla.persistence;

import com.backend.cinejungla.persistence.crud.PeliculaCrudRepository;
import com.backend.cinejungla.persistence.entity.Pelicula;

import java.util.List;
import java.util.Optional;

public class PeliculaRepository {

    private PeliculaCrudRepository peliculaCrudRepository;

    public List <Pelicula> getAll(){
        return (List<Pelicula>) peliculaCrudRepository.getAll();
    }

    public Optional<Pelicula> getAll(int codigoPelicula){
        return (Optional<Pelicula>) peliculaCrudRepository.getPeliculaByCodigo(codigoPelicula);
    }

}
