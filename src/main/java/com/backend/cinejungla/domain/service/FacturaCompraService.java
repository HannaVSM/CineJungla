package com.backend.cinejungla.domain.service;

import com.backend.cinejungla.persistence.crud.FacturaCompraCrudRepository;
import com.backend.cinejungla.persistence.entity.FacturaCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FacturaCompraService {

    @Autowired
    private FacturaCompraCrudRepository facturaCompraCrudRepository;

    public List<FacturaCompra> getAll(){
        return facturaCompraCrudRepository.getAll();
    }

    public Optional<List<FacturaCompra>> getFacturasCliente(int cedulaCliente){
        return Optional.ofNullable(facturaCompraCrudRepository.getFacturasCliente(cedulaCliente));
    }

    public Optional<FacturaCompra> getFacturaByClienteAndFecha(int cedulaCliente, Date fechaFactura){
        return facturaCompraCrudRepository.getFacturaByClienteAndFecha(cedulaCliente, fechaFactura);
    }

    public void insertarRegistro(boolean puntosRedimidos, double totalPago, int cedulaCliente, Date fechaFactura){
        facturaCompraCrudRepository.registrarFactura(puntosRedimidos, totalPago, cedulaCliente, fechaFactura);
    }

}
