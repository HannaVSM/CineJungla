package com.backend.cinejungla.web.controller;

import com.backend.cinejungla.domain.service.SalaService;
import com.backend.cinejungla.persistence.entity.Sala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = ("http://localhost:4200"))
@RestController
@RequestMapping("/sala")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @GetMapping("/{codigo}")
    public Optional<Sala> getSalaByCodigo(@PathVariable("codigo") int codigoSala){
        return (Optional<Sala>) salaService.getSalaByCodigo(codigoSala);
    }

    @GetMapping("/multiplex/{codigo}")
    public List<Sala> getSalasByCodigoMultiplex(@PathVariable("codigo") int codigoMultiplex) { return (List<Sala>) salaService.getSalasByCodigoMultiplex(codigoMultiplex); }
}
