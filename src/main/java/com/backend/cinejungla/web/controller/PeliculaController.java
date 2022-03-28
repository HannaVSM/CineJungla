package com.backend.cinejungla.web.controller;

import com.backend.cinejungla.domain.service.PeliculaEnMultiplexService;
import com.backend.cinejungla.domain.service.PeliculaService;
import com.backend.cinejungla.persistence.entity.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = ("http://localhost:4200"))
@RestController
@RequestMapping("/pelicula")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping("/all")
    public List<Pelicula> getAll(){
        return (List<Pelicula>) peliculaService.getAll();
    }

    @GetMapping("/{codigo}")
    public Optional<Pelicula> getPeliculaByCodigo(@PathVariable("codigo") int codigoPelicula){
        return (Optional<Pelicula>) peliculaService.getPeliculaByCodigo(codigoPelicula);
    }
}
