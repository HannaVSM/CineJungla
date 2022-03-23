package com.backend.cinejungla.persistence.crud;

import com.backend.cinejungla.persistence.entity.VentaSnack;
import com.backend.cinejungla.persistence.entity.VentaSnackPK;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VentaSnackCrudRepository extends CrudRepository <VentaSnack, VentaSnackPK> {

    Optional <VentaSnack> findByStockActualGreaterThan (int stockActual);
}
