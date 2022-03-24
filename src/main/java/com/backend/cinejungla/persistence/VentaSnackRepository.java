package com.backend.cinejungla.persistence;

import com.backend.cinejungla.persistence.crud.VentaSnackCrudRepository;
import com.backend.cinejungla.persistence.entity.Snack;
import com.backend.cinejungla.persistence.entity.VentaSnack;

import java.util.List;
import java.util.Optional;

public class VentaSnackRepository {

    private VentaSnackCrudRepository ventaSnackCrudRepository;

    public Optional <VentaSnack> disponibilidadSnack(int stock){
        return ventaSnackCrudRepository.findByStockActualGreaterThan(0);
    }

    public List<VentaSnack> getAllByCodigoMultiplex(int codigoMultiplex){ return (List<VentaSnack>) ventaSnackCrudRepository.getAllByCodigoMultiplex(codigoMultiplex); }
}
