package com.backend.cinejungla.web.controller;

import com.backend.cinejungla.domain.service.PeliculaEnMultiplexService;
import com.backend.cinejungla.persistence.entity.PeliculaEnMultiplex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pelMul")
public class PeliculaEnMultiplexController {

    @Autowired
    private PeliculaEnMultiplexService peliculaEnMultiplexService;

    @GetMapping("/{codigo}")
    public List<PeliculaEnMultiplex> disponibilidadPelicula(@PathVariable("codigo") int codigoMultiplex){
        return (List<PeliculaEnMultiplex>) peliculaEnMultiplexService.disponibilidadPelicula(codigoMultiplex);

    }
}
