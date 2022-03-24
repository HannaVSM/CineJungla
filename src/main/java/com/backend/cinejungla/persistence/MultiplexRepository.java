package com.backend.cinejungla.persistence;

import com.backend.cinejungla.persistence.crud.MultiplexCrudRepository;
import com.backend.cinejungla.persistence.entity.Multiplex;

import java.util.List;
import java.util.Optional;

public class MultiplexRepository {

    private MultiplexCrudRepository multiplexCrudRepository;

    public List<Multiplex> getAll() { return (List<Multiplex>) multiplexCrudRepository.getAll(); }

    public Optional<Multiplex> getMultiplexbyCodigo(int codigoMultiplex){ return (Optional<Multiplex>) multiplexCrudRepository.findByCodigo(codigoMultiplex);}
}
