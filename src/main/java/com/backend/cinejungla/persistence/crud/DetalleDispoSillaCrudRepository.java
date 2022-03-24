package com.backend.cinejungla.persistence.crud;

import com.backend.cinejungla.persistence.entity.DetalleDispoSilla;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DetalleDispoSillaCrudRepository extends CrudRepository <DetalleDispoSilla, Integer> {
    //Query Method
    List <DetalleDispoSilla> findByDisponibilidadSillaEquals(boolean disponibilidadSilla);

    @Query(value = "SELECT * FROM detalle_dispo_silla WHERE codigo_silla = ? AND codigo_funcion = ?", nativeQuery = true)
    Optional<DetalleDispoSilla> getDetalleByCodigoSillaAndFuncion(int codigoSilla, int codigoFuncion);
}
