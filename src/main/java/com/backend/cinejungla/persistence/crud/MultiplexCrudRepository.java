package com.backend.cinejungla.persistence.crud;

import com.backend.cinejungla.persistence.entity.Multiplex;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MultiplexCrudRepository extends CrudRepository <Multiplex, Integer> {

    @Query(value ="SELECT * FROM multiplex", nativeQuery = true)
    List<Multiplex> getAll();

    @Query(value ="SELECT * FROM multiplex WHERE codigo_multiplex = ?", nativeQuery = true)
    Optional<Multiplex> findByCodigo(int codigoMultiplex);
}
