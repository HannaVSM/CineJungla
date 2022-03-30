package com.backend.cinejungla.web.controller;


import com.backend.cinejungla.domain.service.ClienteService;
import com.backend.cinejungla.persistence.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
