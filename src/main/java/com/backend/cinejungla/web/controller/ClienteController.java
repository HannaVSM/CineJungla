package com.backend.cinejungla.web.controller;


import com.backend.cinejungla.domain.service.ClienteService;
import com.backend.cinejungla.persistence.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{cedula}")
    public Optional<Cliente> getByCedula(@PathVariable("cedula") int cedulaCliente){
        return  clienteService.getByCedula(cedulaCliente);
    }

    @GetMapping("/{usuario}")
    public Optional<Cliente> iniciarSesion(@PathVariable("usuario") String usuarioCliente, String passwordCliente){
        return (Optional<Cliente>) clienteService.inicioSesion(usuarioCliente, passwordCliente);
    }
}
