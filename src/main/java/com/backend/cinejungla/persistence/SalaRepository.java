package com.backend.cinejungla.persistence;

import com.backend.cinejungla.persistence.crud.SalaCrudRepository;
import com.backend.cinejungla.persistence.entity.Sala;

import java.util.List;
import java.util.Optional;

public class SalaRepository {

    private SalaCrudRepository salaCrudRepository;

    public Optional<Sala> getSalaByCodigo(int codigoSala){
        return (Optional<Sala>) salaCrudRepository.findByCodigo(codigoSala);
    }

    public List<Sala> getSalasByCodigoMultiplex(int codigoMultiplex) { return (List<Sala>) salaCrudRepository.getSalasByCodigoMultiplex(codigoMultiplex); }
}
