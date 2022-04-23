package com.backend.cinejungla.web.controller;


import com.backend.cinejungla.domain.service.ClienteService;
import com.backend.cinejungla.persistence.entity.Cliente;
import com.backend.cinejungla.web.manejoPatrones.FachadaPatrones;
import com.backend.cinejungla.web.procesoCompra.FacturaCompraTM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = ("http://localhost:4200"))
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{cedula}")
    public Optional<Cliente> getByCedula(@PathVariable("cedula") int cedulaCliente){
        return  clienteService.getByCedula(cedulaCliente);
    }

    @GetMapping("/sesion/{usuario}/{password}")
    public Optional<Cliente> iniciarSesion(@PathVariable("usuario") String usuarioCliente, @PathVariable("password") String passwordCliente){
        return (Optional<Cliente>) clienteService.inicioSesion(usuarioCliente, passwordCliente);
    }

    @PostMapping("registrarCliente")
    public void registrarCliente(@RequestBody Cliente cliente){
        clienteService.registrarCliente(cliente);
    }

    @GetMapping("/consultarPerfil")
    public Cliente consultarPerfil(){
        return clienteService.consularPerfil();
    }

    @GetMapping("/mostrarMensaje")
    public String mostrarMensaje(){
        return FachadaPatrones.mostrarMensaje();
    }

    @GetMapping("/consultarPerfil/consultarFacturas")
    public List<FacturaCompraTM> consultarFacturas(){
        return clienteService.consultarFacturas();
    }
}
