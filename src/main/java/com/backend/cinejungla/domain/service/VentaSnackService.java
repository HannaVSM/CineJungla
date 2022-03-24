package com.backend.cinejungla.domain.service;

import com.backend.cinejungla.persistence.crud.VentaSnackCrudRepository;
import com.backend.cinejungla.persistence.entity.Snack;
import com.backend.cinejungla.persistence.entity.VentaSnack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaSnackService {

    @Autowired
    private VentaSnackCrudRepository ventaSnackCrudRepository;

    public Optional <VentaSnack> disponibilidadSnack(int stock){
        return ventaSnackCrudRepository.findByStockActualGreaterThan(0);
    }

    public List<VentaSnack> getAllByCodigoMultiplex(int codigoMultiplex){ return (List<VentaSnack>) ventaSnackCrudRepository.getAllByCodigoMultiplex(codigoMultiplex); }
}
