package com.backend.cinejungla.domain.service;

import com.backend.cinejungla.persistence.crud.EmpleadoCrudRepository;
import com.backend.cinejungla.persistence.entity.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoCrudRepository empleadoCrudRepository;

    public Optional<Empleado> getEmpleado(int cedulaEmpleado){
        return empleadoCrudRepository.findById(cedulaEmpleado);
    }
}
