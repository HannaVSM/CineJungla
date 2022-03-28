package com.backend.cinejungla.web.controller;


import com.backend.cinejungla.domain.service.FuncionService;
import com.backend.cinejungla.persistence.entity.Funcion;
import com.backend.cinejungla.web.procesoCompra.Pedido;
import com.backend.cinejungla.web.procesoCompra.ProcesoCompra;
import com.backend.cinejungla.web.procesoCompra.ProcesoConcreto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@CrossOrigin(origins = ("http://localhost:4200"))
@RestController
@RequestMapping("/funcion")
public class FuncionController {

    @Autowired
    private FuncionService funcionService;

    @GetMapping("/{codigoMultiplex}/{codigoPelicula}/{fecha}")
    public List<Funcion> getFuncionesByPeliculaAndFecha(@PathVariable("codigoMultiplex") int codigoMultiplex, @PathVariable("codigoPelicula") int codigoPelicula, @PathVariable("fecha") Date fechaFuncion){

        ProcesoCompra proceso = new ProcesoConcreto();

        //List<Funcion> funciones = proceso.pruebaFunciones(codigoMultiplex, codigoPelicula, fechaFuncion);

        //Pedido pedido = (Pedido)proceso.leerArchivo();

        //System.out.println(pedido.getMultiplex().getNombreMultiplex());

        return proceso.consultarFunciones(codigoMultiplex, codigoPelicula, fechaFuncion);

        //return funciones;

        //return funcionService.getFuncionesByPeliculaAndFecha(codigoPelicula, fechaFuncion);
    }
}
