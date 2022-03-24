package com.backend.cinejungla.web.controller;

import com.backend.cinejungla.domain.service.FacturaCompraService;
import com.backend.cinejungla.persistence.entity.FacturaCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/factura")
public class FacturaCompraController {

    @Autowired
    private FacturaCompraService facturaCompraService;

    @GetMapping("/{cedula}")
    public Optional<List<FacturaCompra>> getFacturasCliente(@PathVariable("cedula") int cedulaCliente){
        return facturaCompraService.getFacturasCliente(cedulaCliente);
    }
}
