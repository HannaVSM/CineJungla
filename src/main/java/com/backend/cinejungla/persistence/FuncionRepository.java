package com.backend.cinejungla.persistence;

import com.backend.cinejungla.persistence.crud.FuncionCrudRepository;
import com.backend.cinejungla.persistence.entity.Funcion;

import java.sql.Date;
import java.util.List;

public class FuncionRepository {

    private FuncionCrudRepository funcionCrudRepository;

    public List<Funcion> getFuncionesByPeliculaAndFecha(int codigoPelicula, Date fechaFuncion){
        return (List<Funcion>) funcionCrudRepository.getFuncionByPeliculaAndFecha(codigoPelicula, fechaFuncion);
    }
}
