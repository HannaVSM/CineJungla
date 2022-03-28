package com.backend.cinejungla.web.controller;

import com.backend.cinejungla.domain.service.MultiplexService;
import com.backend.cinejungla.persistence.entity.Multiplex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = ("http://localhost:4200"))
@RestController
@RequestMapping("/multiplex")
public class MultiplexController {

    @Autowired
    private MultiplexService multiplexService;

    @GetMapping("/all")
    public List<Multiplex> getAll() {
        return (List<Multiplex>) multiplexService.getAll();
    }

    @GetMapping("/{codigo}")
    public Optional<Multiplex> getMultiplexbyCodigo(@PathVariable("codigo") int codigoMultiplex) {
        return (Optional<Multiplex>) multiplexService.getMultiplexbyCodigo(codigoMultiplex);
    }
}
