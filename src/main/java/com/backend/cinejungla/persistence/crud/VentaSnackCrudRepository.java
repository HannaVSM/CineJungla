package com.backend.cinejungla.persistence.crud;

import com.backend.cinejungla.persistence.entity.VentaSnack;
import com.backend.cinejungla.persistence.entity.VentaSnackPK;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface VentaSnackCrudRepository extends CrudRepository <VentaSnack, VentaSnackPK> {

    Optional <List<VentaSnack>> findByStockActualGreaterThan (int stockActual);

    @Query(value = "SELECT * FROM venta_snack where codigo_multiplex = ?", nativeQuery = true)
    List<VentaSnack> getAllByCodigoMultiplex(int codigoMultiplex);

    @Query(value = "SELECT * FROM venta_snack where codigo_multiplex = ? AND codigo_snack = ?", nativeQuery = true)
    Optional<VentaSnack> getByCodigoMultiplexAndSnack(int codigoMultiplex, int codigoSnack);

    @Modifying
    @Query(value = "UPDATE venta_snack SET stock_actual = :stockNuevo WHERE codigo_multiplex = :codigoMultiplex AND codigo_snack = :codigoSnack", nativeQuery = true)
    @Transactional
    void actualizarVentaSnack(@Param("stockNuevo") int stockNuevo, @Param("codigoMultiplex") int codigoMultiplex, @Param("codigoSnack") int codigoSnack);
}
