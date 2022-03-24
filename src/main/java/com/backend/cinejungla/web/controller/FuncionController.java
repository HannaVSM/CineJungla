package com.backend.cinejungla.web.controller;


import com.backend.cinejungla.domain.service.FuncionService;
import com.backend.cinejungla.persistence.entity.Funcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/funcion")
public class FuncionController {

    @Autowired
    private FuncionService funcionService;

    @GetMapping("/{codigo}/{fecha}")
    public List<Funcion> getFuncionesByPeliculaAndFecha(@PathVariable("codigo") int codigoPelicula, @PathVariable("fecha") Date fechaFuncion){

        return funcionService.getFuncionesByPeliculaAndFecha(codigoPelicula, fechaFuncion);
    }
}
