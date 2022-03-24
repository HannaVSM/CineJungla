package com.backend.cinejungla.web.controller;

import com.backend.cinejungla.domain.service.CargoOcupadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cargoOcupado")
public class CargoOcupadoController {

    @Autowired
    private CargoOcupadoService cargoOcupadoService;
}
