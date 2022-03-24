package com.backend.cinejungla.web.controller;

import com.backend.cinejungla.domain.service.CargoService;
import com.backend.cinejungla.persistence.entity.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cargo")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @GetMapping("/all")
    public List <Cargo> getAll(){
        return cargoService.getAll();
    }
}
