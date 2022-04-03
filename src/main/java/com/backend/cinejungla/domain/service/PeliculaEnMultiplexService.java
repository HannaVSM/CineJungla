package com.backend.cinejungla.domain.service;

import com.backend.cinejungla.persistence.crud.PeliculaEnMultiplexCrudRepository;
import com.backend.cinejungla.persistence.entity.Multiplex;
import com.backend.cinejungla.persistence.entity.PeliculaEnMultiplex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PeliculaEnMultiplexService {

    @Autowired
    private PeliculaEnMultiplexCrudRepository peliculaEnMultiplexCrudRepository;

    @Autowired
    private MultiplexService multiplexService;

    public List<Multiplex> disponibilidadPelicula(int codigoPelicula){
        List<PeliculaEnMultiplex> peliculasMultiplex = (List<PeliculaEnMultiplex>) peliculaEnMultiplexCrudRepository.disponibilidadPelicula(codigoPelicula);

        List<Multiplex> listaMultiplex = new ArrayList<>();

        for(int i=0; i<peliculasMultiplex.size(); i++){
            int codigoMultiplex = peliculasMultiplex.get(i).getId().getCodigoMultiplex();

            Optional<Multiplex> multiplex = multiplexService.getMultiplexbyCodigo(codigoMultiplex);
            listaMultiplex.add(multiplex.get());
        }
        return listaMultiplex;
    }
}
