package com.backend.cinejungla.web.controller;

import com.backend.cinejungla.domain.service.FacturaCompraService;
import com.backend.cinejungla.persistence.entity.FacturaCompra;
import com.backend.cinejungla.web.procesoCompra.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = ("http://localhost:4200"))
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

    @GetMapping("/pagoFactura/{redimirPuntos}")
    public void pagoFactura(@PathVariable("redimirPuntos") boolean redimirPuntos){
        ProcesoCompra proceso = new ProcesoConcreto();
        proceso.pagarFactura(redimirPuntos);
    }
}
