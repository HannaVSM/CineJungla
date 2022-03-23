package com.backend.cinejungla.persistence;

import com.backend.cinejungla.persistence.crud.SillaCrudRepository;
import com.backend.cinejungla.persistence.entity.Silla;

import java.util.List;

public class SillaRepository {

    private SillaCrudRepository sillaCrudRepository;

    List<Silla> getSillaByPrice (int precioSilla){
        return sillaCrudRepository.sillaByPrice(precioSilla);
    }
}
