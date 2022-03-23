package com.backend.cinejungla.persistence;

import com.backend.cinejungla.persistence.crud.DetalleDispoSillaCrudRepository;
import com.backend.cinejungla.persistence.entity.DetalleDispoSilla;

import java.util.List;
import java.util.Optional;

public class DetalleDispoSillaRepository {

    private DetalleDispoSillaCrudRepository detalleDispoSillaCrudRepository;

    public Optional<List<DetalleDispoSilla>> getSillasDisponibles(){
        return Optional.ofNullable(detalleDispoSillaCrudRepository.findByDisponibilidadSillaEquals(true));
    }
}
