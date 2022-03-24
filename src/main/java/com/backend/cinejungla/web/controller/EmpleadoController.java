package com.backend.cinejungla.web.controller;


import com.backend.cinejungla.domain.service.EmpleadoService;
import com.backend.cinejungla.persistence.entity.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
