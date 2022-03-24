package com.backend.cinejungla.domain.service;

import com.backend.cinejungla.persistence.crud.CargoCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CargoOcupadoService {

    @Autowired
    private CargoCrudRepository cargoCrudRepository;
}
