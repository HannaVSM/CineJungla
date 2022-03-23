package com.backend.cinejungla.persistence;

import com.backend.cinejungla.persistence.crud.EmpleadoCrudRepository;
import com.backend.cinejungla.persistence.entity.Empleado;

import java.util.Optional;

public class EmpleadoRepository {

    private EmpleadoCrudRepository empleadoCrudRepository;

    public Optional<Empleado> getAllEmpleados(int cedulaEmpleado){
        return empleadoCrudRepository.findById(cedulaEmpleado);
    }
}
