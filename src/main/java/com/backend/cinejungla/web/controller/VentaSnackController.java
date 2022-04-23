package com.backend.cinejungla.web.controller;

import com.backend.cinejungla.domain.service.VentaSnackService;
import com.backend.cinejungla.persistence.entity.VentaSnack;
import com.backend.cinejungla.web.procesoCompra.SnackTM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = ("http://localhost:4200"))
@RestController
@RequestMapping("/ventaSnack")
public class VentaSnackController {

    @Autowired
    private VentaSnackService ventaSnackService;

    @GetMapping("/stock/{stock}")
    public Optional<List<VentaSnack>> disponibilidadSnack(@PathVariable("stock") int stock){
        return ventaSnackService.disponibilidadSnack(stock);
    }

    @GetMapping("/{codigo}")
    public List<VentaSnack> getAllByCodigoMultiplex(@PathVariable("codigo") int codigoMultiplex){ return (List<VentaSnack>) ventaSnackService.getAllByCodigoMultiplex(codigoMultiplex); }

    @GetMapping("/ventaSnacks/{codigoMultiplex}")
    public List<SnackTM> getVentaSnacksInMultiplex(@PathVariable("codigoMultiplex") int codigoMultiplex){ return (List<SnackTM>) ventaSnackService.getVentaSnacksInMultiplex(codigoMultiplex); }
}
