package com.backend.cinejungla.persistence.crud;

import com.backend.cinejungla.persistence.entity.Snack;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SnackCrudRepository extends CrudRepository <Snack, Integer> {
    @Query(value ="SELECT * FROM snack WHERE codigo_snack = ?", nativeQuery = true)
    Optional<Snack> getSnackByCodigo(int codigoSnack);
}
