package com.backend.cinejungla.web.controller;


import com.backend.cinejungla.domain.service.DetalleDispoSillaService;
import com.backend.cinejungla.persistence.entity.DetalleDispoSilla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/DispoSilla")
//Aún no se puede probar porque no hay registros
public class DetalleDispoSillaController {

    @Autowired
    private DetalleDispoSillaService detalleDispoSillaService;


    @GetMapping("/sillaDispoXd")
    public Optional<List<DetalleDispoSilla>> getSillasDispo(){
        return detalleDispoSillaService.getSillasDisponibles();
    }

    @GetMapping("/{codigoSilla}")
    public Optional<DetalleDispoSilla> getDetalleByCodigoSilla(int codigoSilla, int codigoFuncion){
        return (Optional<DetalleDispoSilla>) detalleDispoSillaService.getDetalleByCodigoSillaAndFuncion(codigoSilla, codigoFuncion);
    }
}
