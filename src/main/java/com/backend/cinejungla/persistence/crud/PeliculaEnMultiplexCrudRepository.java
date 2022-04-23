package com.backend.cinejungla.persistence.crud;

import com.backend.cinejungla.persistence.entity.PeliculaEnMultiplex;
import com.backend.cinejungla.persistence.entity.PeliculaEnMultiplexPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PeliculaEnMultiplexCrudRepository extends CrudRepository <PeliculaEnMultiplex, PeliculaEnMultiplexPK> {

    @Query(value = "SELECT * FROM pelicula_en_multiplex WHERE codigo_pelicula = ? AND pelicula_en_cartelera = true", nativeQuery = true)
    List<PeliculaEnMultiplex> disponibilidadPelicula(int codigoPelicula);
}
