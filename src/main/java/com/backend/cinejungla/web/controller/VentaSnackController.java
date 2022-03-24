package com.backend.cinejungla.web.controller;

import com.backend.cinejungla.domain.service.VentaSnackService;
import com.backend.cinejungla.persistence.crud.VentaSnackCrudRepository;
import com.backend.cinejungla.persistence.entity.VentaSnack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ventaSnack")
public class VentaSnackController {

    @Autowired
    private VentaSnackService ventaSnackService;

    //Revisar
    @GetMapping("/stock")
    public Optional<VentaSnack> disponibilidadSnack(int stock){
        return ventaSnackService.disponibilidadSnack(0);
    }

    @GetMapping("/{codigo}")
    public List<VentaSnack> getAllByCodigoMultiplex(@PathVariable("codigo") int codigoMultiplex){ return (List<VentaSnack>) ventaSnackService.getAllByCodigoMultiplex(codigoMultiplex); }
}
