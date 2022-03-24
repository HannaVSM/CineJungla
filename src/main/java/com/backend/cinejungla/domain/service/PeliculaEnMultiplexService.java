package com.backend.cinejungla.domain.service;

import com.backend.cinejungla.persistence.crud.PeliculaEnMultiplexCrudRepository;
import com.backend.cinejungla.persistence.entity.PeliculaEnMultiplex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaEnMultiplexService {

    @Autowired
    private PeliculaEnMultiplexCrudRepository peliculaEnMultiplexCrudRepository;

    //Revisar, estoy muy tostada. nota2: Parece que está bien
    public List<PeliculaEnMultiplex> disponibilidadPelicula(int codigoMultiplex){
        return (List<PeliculaEnMultiplex>) peliculaEnMultiplexCrudRepository.disponibilidadPelicula(codigoMultiplex);

    }
}
