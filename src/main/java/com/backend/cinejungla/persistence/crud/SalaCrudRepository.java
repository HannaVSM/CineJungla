package com.backend.cinejungla.persistence.crud;

import com.backend.cinejungla.persistence.entity.Sala;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SalaCrudRepository extends CrudRepository <Sala, Integer> {

    @Query(value ="SELECT * FROM sala WHERE codigo_sala = ?", nativeQuery = true)
    Optional<Sala> findByCodigo(int codigoSala);

    @Query(value ="SELECT * FROM sala WHERE codigo_multiplex = ?", nativeQuery = true)
    List<Sala> getSalasByCodigoMultiplex(int codigoMultiplex);


}
