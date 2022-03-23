package com.backend.cinejungla.persistence.crud;

import com.backend.cinejungla.persistence.entity.Empleado;
import org.springframework.data.repository.CrudRepository;

public interface EmpleadoCrudRepository extends CrudRepository <Empleado, Integer> {
}
