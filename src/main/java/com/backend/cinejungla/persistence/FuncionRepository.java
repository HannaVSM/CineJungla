package com.backend.cinejungla.persistence;

import com.backend.cinejungla.persistence.crud.FuncionCrudRepository;
import com.backend.cinejungla.persistence.entity.Funcion;

import java.util.List;

public class FuncionRepository {

    private FuncionCrudRepository funcionCrudRepository;

    public List<Funcion> getFuncionesPeliculas(int codigoPelicula){
        return funcionCrudRepository.getFuncionPelicula(codigoPelicula);
    }
}
