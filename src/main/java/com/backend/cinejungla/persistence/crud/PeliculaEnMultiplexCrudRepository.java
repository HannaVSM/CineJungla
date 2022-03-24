package com.backend.cinejungla.persistence.crud;

import com.backend.cinejungla.persistence.entity.PeliculaEnMultiplex;
import com.backend.cinejungla.persistence.entity.PeliculaEnMultiplexPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PeliculaEnMultiplexCrudRepository extends CrudRepository <PeliculaEnMultiplex, PeliculaEnMultiplexPK> {

    @Query(value = "SELECT * FROM pelicula_en_multiplex WHERE codigo_multiplex = ?", nativeQuery = true)
    List<PeliculaEnMultiplex> disponibilidadPelicula(int codigoMultiplex);
}
