package com.backend.cinejungla.persistence.crud;

import com.backend.cinejungla.persistence.entity.Pelicula;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PeliculaCrudRepository extends CrudRepository <Pelicula, Integer> {

    @Query(value = "SELECT * FROM pelicula", nativeQuery = true)
    List<Pelicula> getAll();

    @Query(value = "SELECT * FROM pelicula WHERE codigo_pelicula = ?", nativeQuery = true)
    Optional<Pelicula> getPeliculaByCodigo(int codigoPelicula);
}
