package com.backend.cinejungla.domain.service;

import com.backend.cinejungla.persistence.crud.SillaCrudRepository;
import com.backend.cinejungla.persistence.entity.Silla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SillaService {

    @Autowired
    private SillaCrudRepository sillaCrudRepository;

    public Optional<Silla> getSillaByCodigo(int codigoSilla){
        return sillaCrudRepository.sillaByCodigo(codigoSilla);
    }

    public List<Silla> getSillaByPrice (int precioSilla){
        return sillaCrudRepository.sillaByPrice(precioSilla);
    }

    public List<Silla> getSillasByCodigoSala (int codigoSala){ return (List<Silla>) sillaCrudRepository.sillasByCodigoSala(codigoSala); }

    public List<Silla> getSillasByCodigoSalaAndTipo (int codigoSala, String tipoSilla){
        return (List<Silla>) sillaCrudRepository.sillasByCodigoSalaAndTipo(codigoSala, tipoSilla);
    }
}
