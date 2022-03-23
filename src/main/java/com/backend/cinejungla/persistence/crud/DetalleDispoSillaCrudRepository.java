package com.backend.cinejungla.persistence.crud;

import com.backend.cinejungla.persistence.entity.DetalleDispoSilla;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DetalleDispoSillaCrudRepository extends CrudRepository <DetalleDispoSilla, Integer> {
    //Query Method
    List <DetalleDispoSilla> findByDisponibilidadSillaEquals(boolean disponibilidadSilla);
}
