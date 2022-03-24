package com.backend.cinejungla.domain.service;

import com.backend.cinejungla.persistence.crud.SalaCrudRepository;
import com.backend.cinejungla.persistence.entity.Sala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaService {

    @Autowired
    private SalaCrudRepository salaCrudRepository;

    public Optional<Sala> getSalaByCodigo(int codigoSala){
        return (Optional<Sala>) salaCrudRepository.findByCodigo(codigoSala);
    }

    public List<Sala> getSalasByCodigoMultiplex(int codigoMultiplex) { return (List<Sala>) salaCrudRepository.getSalasByCodigoMultiplex(codigoMultiplex); }
}
