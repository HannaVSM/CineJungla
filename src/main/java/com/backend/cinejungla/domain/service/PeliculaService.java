package com.backend.cinejungla.domain.service;

import com.backend.cinejungla.persistence.crud.PeliculaCrudRepository;
import com.backend.cinejungla.persistence.entity.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaCrudRepository peliculaCrudRepository;

    public List <Pelicula> getAll(){
        return (List<Pelicula>) peliculaCrudRepository.getAll();
    }

    public Optional<Pelicula> getPeliculaByCodigo(int codigoPelicula){
        return (Optional<Pelicula>) peliculaCrudRepository.getPeliculaByCodigo(codigoPelicula);
    }

}
