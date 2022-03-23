package com.backend.cinejungla.persistence;

import com.backend.cinejungla.persistence.crud.CargoCrudRepository;
import com.backend.cinejungla.persistence.entity.Cargo;

import java.util.List;

public class CargoRepository {

    private CargoCrudRepository cargoCrudRepository;

    public List <Cargo> getAll(){
        return (List<Cargo>) cargoCrudRepository.findAll();
    }
}
