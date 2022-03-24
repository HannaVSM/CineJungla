package com.backend.cinejungla.domain.service;

import com.backend.cinejungla.persistence.crud.FuncionCrudRepository;
import com.backend.cinejungla.persistence.entity.Funcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class FuncionService {

    @Autowired
    private FuncionCrudRepository funcionCrudRepository;

    public List<Funcion> getFuncionesByPeliculaAndFecha(int codigoPelicula, Date fechaFuncion){
        return (List<Funcion>) funcionCrudRepository.getFuncionByPeliculaAndFecha(codigoPelicula, fechaFuncion);
    }
}
