package com.backend.cinejungla.persistence;

import com.backend.cinejungla.persistence.crud.SillaCrudRepository;
import com.backend.cinejungla.persistence.entity.Silla;

import java.util.List;

public class SillaRepository {

    private SillaCrudRepository sillaCrudRepository;

    List<Silla> getSillaByPrice (int precioSilla){
        return sillaCrudRepository.sillaByPrice(precioSilla);
    }

    List<Silla> getSillasByCodigoSala (int codigoSala){ return (List<Silla>) sillaCrudRepository.sillasByCodigoSala(codigoSala); }

    List<Silla> getSillasByCodigoSalaAndTipo (int codigoSala, String tipoSilla){
        return (List<Silla>) sillaCrudRepository.sillasByCodigoSalaAndTipo(codigoSala, tipoSilla);
    }
}
