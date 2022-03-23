package com.backend.cinejungla.persistence;

import com.backend.cinejungla.persistence.crud.VentaSnackCrudRepository;
import com.backend.cinejungla.persistence.entity.VentaSnack;

import java.util.Optional;

public class VentaSnackRepository {

    private VentaSnackCrudRepository ventaSnackCrudRepository;

    public Optional <VentaSnack> disponibilidadSnack(int stock){
        return ventaSnackCrudRepository.findByStockActualGreaterThan(0);
    }
}
