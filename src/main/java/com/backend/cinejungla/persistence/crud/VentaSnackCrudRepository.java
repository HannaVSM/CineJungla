package com.backend.cinejungla.persistence.crud;

import com.backend.cinejungla.persistence.entity.VentaSnack;
import com.backend.cinejungla.persistence.entity.VentaSnackPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface VentaSnackCrudRepository extends CrudRepository <VentaSnack, VentaSnackPK> {

    Optional <List<VentaSnack>> findByStockActualGreaterThan (int stockActual);

    @Query(value = "SELECT * FROM venta_snack where codigo_multiplex = ?", nativeQuery = true)
    List<VentaSnack> getAllByCodigoMultiplex(int codigoMultiplex);
}
