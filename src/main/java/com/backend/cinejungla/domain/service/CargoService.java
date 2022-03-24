package com.backend.cinejungla.domain.service;

import com.backend.cinejungla.persistence.crud.CargoCrudRepository;
import com.backend.cinejungla.persistence.entity.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoService {

    @Autowired
    private CargoCrudRepository cargoCrudRepository;

    public List <Cargo> getAll(){
        return (List<Cargo>) cargoCrudRepository.findAll();
    }
}
