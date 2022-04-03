package com.backend.cinejungla.domain.service;

import com.backend.cinejungla.persistence.crud.CompraSnackCrudRepository;
import com.backend.cinejungla.persistence.entity.CompraSnack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CompraSnackService {

    @Autowired
    private CompraSnackCrudRepository compraSnackCrudRepository;

    public CompraSnack save(CompraSnack compraSnack){
        return compraSnackCrudRepository.save(compraSnack);
    }

    public List<CompraSnack> getComprasSnackFactura(int codigoFacura){
        return compraSnackCrudRepository.getComprasSnackFactura(codigoFacura);
    }

    public void insertarResgistro(int codigoSnack, int codigoFacturaCompra, int cantidadSnackComprado){
        compraSnackCrudRepository.registrarFactura(codigoSnack, codigoFacturaCompra, cantidadSnackComprado);
    }
}
