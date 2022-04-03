package com.backend.cinejungla.web.controller;

import com.backend.cinejungla.domain.service.PeliculaEnMultiplexService;
import com.backend.cinejungla.persistence.entity.Multiplex;
import com.backend.cinejungla.persistence.entity.PeliculaEnMultiplex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = ("http://localhost:4200"))
@RestController
@RequestMapping("/pelMul")
public class PeliculaEnMultiplexController {

    @Autowired
    private PeliculaEnMultiplexService peliculaEnMultiplexService;

    @GetMapping("/{codigo}")
    public List<Multiplex> disponibilidadPelicula(@PathVariable("codigo") int codigoPelicula){
        return (List<Multiplex>) peliculaEnMultiplexService.disponibilidadPelicula(codigoPelicula);
    }
}
