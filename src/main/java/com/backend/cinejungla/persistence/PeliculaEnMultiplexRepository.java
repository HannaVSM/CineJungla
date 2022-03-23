package com.backend.cinejungla.persistence;

import com.backend.cinejungla.persistence.crud.PeliculaCrudRepository;
import com.backend.cinejungla.persistence.crud.PeliculaEnMultiplexCrudRepository;
import com.backend.cinejungla.persistence.entity.PeliculaEnMultiplex;
import com.backend.cinejungla.persistence.entity.PeliculaEnMultiplexPK;

import java.util.Optional;

public class PeliculaEnMultiplexRepository {

    private PeliculaEnMultiplexCrudRepository peliculaEnMultiplexCrudRepository;

    //Revisar, estoy muy tostada
    public Optional <PeliculaEnMultiplex> disponibilidadPelicula(int id){
        return peliculaEnMultiplexCrudRepository.disponibilidadPelicula(id);

    }
}
