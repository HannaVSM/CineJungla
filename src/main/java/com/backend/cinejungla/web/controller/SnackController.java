package com.backend.cinejungla.web.controller;

import com.backend.cinejungla.domain.service.SnackService;
import com.backend.cinejungla.persistence.entity.Snack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = ("http://localhost:4200"))
@RestController
@RequestMapping("/snack")
public class SnackController {

    @Autowired
    private SnackService snackService;

    @GetMapping("/all")
    public List<Snack> getAll(){return (List<Snack>) snackService.getAll();}

    @GetMapping("/{codigo}")
    public Optional<Snack> getSnackByCodigo(@PathVariable("codigo") int codigoSnack){return (Optional<Snack>) snackService.getSnackByCodigo(codigoSnack);}
}
