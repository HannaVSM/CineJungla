package com.backend.cinejungla.persistence.crud;

import com.backend.cinejungla.persistence.entity.Funcion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface FuncionCrudRepository extends CrudRepository <Funcion, Integer> {

    @Query(value = "SELECT * FROM funcion WHERE codigo_pelicula = ? AND fecha_funcion = ?", nativeQuery = true)
    List<Funcion> getFuncionByPeliculaAndFecha(int codigoPelicula, Date fechaFuncion);

    //@Query(value = "SELECT * FROM funcion WHERE ")

}
