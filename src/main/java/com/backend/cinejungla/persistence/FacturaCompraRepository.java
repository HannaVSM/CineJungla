package com.backend.cinejungla.persistence;

import com.backend.cinejungla.persistence.crud.FacturaCompraCrudRepository;
import com.backend.cinejungla.persistence.entity.FacturaCompra;

import java.util.List;
import java.util.Optional;

public class FacturaCompraRepository {

    private FacturaCompraCrudRepository facturaCompraCrudRepository;

    public Optional<List<FacturaCompra>> getFacturasCliente(int cedulaCliente){
        return Optional.ofNullable(facturaCompraCrudRepository.getFacturasCliente(cedulaCliente));
    }
}
