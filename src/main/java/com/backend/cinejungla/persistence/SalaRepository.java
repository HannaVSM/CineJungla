package com.backend.cinejungla.persistence;

import com.backend.cinejungla.persistence.crud.SalaCrudRepository;
import com.backend.cinejungla.persistence.entity.Sala;

import java.util.Optional;

public class SalaRepository {

    private SalaCrudRepository salaCrudRepository;

    public Optional<Sala> getSalaById(int codigoSala){
        return salaCrudRepository.findById(codigoSala);
    }
}
