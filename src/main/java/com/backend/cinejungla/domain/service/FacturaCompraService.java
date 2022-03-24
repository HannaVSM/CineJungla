package com.backend.cinejungla.domain.service;

import com.backend.cinejungla.persistence.crud.FacturaCompraCrudRepository;
import com.backend.cinejungla.persistence.entity.FacturaCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaCompraService {

    @Autowired
    private FacturaCompraCrudRepository facturaCompraCrudRepository;

    public Optional<List<FacturaCompra>> getFacturasCliente(int cedulaCliente){
        return Optional.ofNullable(facturaCompraCrudRepository.getFacturasCliente(cedulaCliente));
    }
}
