package com.backend.cinejungla.domain.service;

import com.backend.cinejungla.persistence.crud.DetalleDispoSillaCrudRepository;
import com.backend.cinejungla.persistence.entity.DetalleDispoSilla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleDispoSillaService {

    @Autowired
    private DetalleDispoSillaCrudRepository detalleDispoSillaCrudRepository;

    public Optional<List<DetalleDispoSilla>> getSillasDisponibles(){
        return Optional.ofNullable(detalleDispoSillaCrudRepository.findByDisponibilidadSillaEquals(true));
    }
    public Optional<DetalleDispoSilla> getDetalleByCodigoSillaAndFuncion(int codigoSilla, int codigoFuncion){
        return (Optional<DetalleDispoSilla>) detalleDispoSillaCrudRepository.getDetalleByCodigoSillaAndFuncion(codigoSilla, codigoFuncion);
    }

    public void insertarRegistro(int codigoSilla, int codigoFuncion, int codigoFacturaCompra, boolean disponibilidadSilla){
        detalleDispoSillaCrudRepository.registrarFactura(codigoSilla, codigoFuncion, codigoFacturaCompra, disponibilidadSilla);
    }
}
