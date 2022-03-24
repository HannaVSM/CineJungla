package com.backend.cinejungla.persistence;

import com.backend.cinejungla.persistence.crud.PeliculaCrudRepository;
import com.backend.cinejungla.persistence.crud.PeliculaEnMultiplexCrudRepository;
import com.backend.cinejungla.persistence.entity.PeliculaEnMultiplex;
import com.backend.cinejungla.persistence.entity.PeliculaEnMultiplexPK;

import java.util.List;
import java.util.Optional;

public class PeliculaEnMultiplexRepository {

    private PeliculaEnMultiplexCrudRepository peliculaEnMultiplexCrudRepository;

    //Revisar, estoy muy tostada
    public List<PeliculaEnMultiplex> disponibilidadPelicula(int codigoMultiplex){
        return (List<PeliculaEnMultiplex>) peliculaEnMultiplexCrudRepository.disponibilidadPelicula(codigoMultiplex);

    }
}
