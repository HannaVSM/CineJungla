package com.backend.cinejungla.web.controller;

import com.backend.cinejungla.domain.service.CargoOcupadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = ("http://localhost:4200"))
@RestController
@RequestMapping("/cargoOcupado")
public class CargoOcupadoController {

    @Autowired
    private CargoOcupadoService cargoOcupadoService;
}
