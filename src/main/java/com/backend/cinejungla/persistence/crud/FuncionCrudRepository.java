package com.backend.cinejungla.persistence.crud;

import com.backend.cinejungla.persistence.entity.Funcion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FuncionCrudRepository extends CrudRepository <Funcion, Integer> {

    @Query(value = "SELECT * FROM funcion WHERE codigo_pelicula = ?", nativeQuery = true)
    List<Funcion> getFuncionPelicula(int codigoPelicula);
}
