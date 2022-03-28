package com.backend.cinejungla.web.controller;

import com.backend.cinejungla.domain.service.CompraSnackService;
import com.backend.cinejungla.persistence.entity.CompraSnack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = ("http://localhost:4200"))
@RestController
@RequestMapping("/compraSnack")
public class CompraSnackController {

    @Autowired
    private CompraSnackService compraSnackService;

    @PostMapping("/save")
    public CompraSnack save (@RequestBody CompraSnack compraSnack){
        return compraSnackService.save(compraSnack);
    }
}
