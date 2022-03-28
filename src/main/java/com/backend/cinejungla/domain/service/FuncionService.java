package com.backend.cinejungla.domain.service;

import com.backend.cinejungla.persistence.crud.FuncionCrudRepository;
import com.backend.cinejungla.persistence.entity.Funcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionService {


    @Autowired
    private FuncionCrudRepository funcionCrudRepository;


    public Optional<Funcion> getFuncionByCodigo(int codigoFuncion){
        return (Optional<Funcion>) funcionCrudRepository.getFuncionByCodigo(codigoFuncion);
    }


    public List<Funcion> getFuncionesByPeliculaAndFecha(int codigoPelicula, Date fechaFuncion){
        return (List<Funcion>) funcionCrudRepository.getFuncionByPeliculaAndFecha(codigoPelicula, fechaFuncion);
    }
}
