package com.backend.cinejungla.web.controller;


import com.backend.cinejungla.domain.service.EmpleadoService;
import com.backend.cinejungla.persistence.entity.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = ("http://localhost:4200"))
@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/{cedulaE}")
    public Optional <Empleado> getEmpleado(@PathVariable("cedulaE") int cedulaEmpleado){
        return empleadoService.getEmpleado(cedulaEmpleado);
    }
}
