package com.backend.cinejungla.web.controller;

import com.backend.cinejungla.domain.service.FacturaCompraService;
import com.backend.cinejungla.persistence.entity.FacturaCompra;
import com.backend.cinejungla.web.procesoCompra.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.JsonPath;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/seleccionarSillas")
    public void seleccionarSillas(@RequestBody List<SillaTM> sillasTM){
        ProcesoCompra proceso = new ProcesoConcreto();
        proceso.seleccionarSillas(sillasTM);
    }

    @PostMapping("/seleccionarSnacks")
    public FacturaCompraTM seleccionarSnacks(@RequestBody Optional<List<SnackTM>> snacksTM){
        ProcesoCompra proceso = new ProcesoConcreto();
        return proceso.seleccionarSnacks(snacksTM);
    }

    @PostMapping("/prueba")
    public void pruebaJson(@RequestBody List<SillaTM> sillasTM){
        System.out.println(sillasTM.get(0).getUbicacionSilla());
        System.out.println(sillasTM.get(1).getUbicacionSilla());
    }

    @PostMapping("/pagoFactura/{puntosRedimidos}")
    public void pagoFactura(@PathVariable("puntosRedimidos") boolean puntosRedimidos){
        ProcesoCompra proceso = new ProcesoConcreto();
        proceso.pagarFactura(puntosRedimidos);
    }
}
