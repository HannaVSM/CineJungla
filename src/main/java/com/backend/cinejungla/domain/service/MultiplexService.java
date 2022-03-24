package com.backend.cinejungla.domain.service;

import com.backend.cinejungla.persistence.crud.MultiplexCrudRepository;
import com.backend.cinejungla.persistence.entity.Multiplex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MultiplexService {

    @Autowired
    private MultiplexCrudRepository multiplexCrudRepository;

    public List<Multiplex> getAll() { return (List<Multiplex>) multiplexCrudRepository.getAll(); }

    public Optional<Multiplex> getMultiplexbyCodigo(int codigoMultiplex){ return (Optional<Multiplex>) multiplexCrudRepository.findByCodigo(codigoMultiplex);}
}
