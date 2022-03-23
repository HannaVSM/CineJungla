package com.backend.cinejungla.persistence;

import com.backend.cinejungla.persistence.crud.MultiplexCrudRepository;
import com.backend.cinejungla.persistence.entity.Multiplex;

import java.util.List;

public class MultiplexRepository {

    private MultiplexCrudRepository multiplexCrudRepository;

    public List<Multiplex> getMultiplexs(){
        return (List<Multiplex>) multiplexCrudRepository.findAll();
    }
}
