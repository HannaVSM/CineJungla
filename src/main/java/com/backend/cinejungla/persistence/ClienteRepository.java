package com.backend.cinejungla.persistence;

import com.backend.cinejungla.persistence.crud.ClienteCrudRepository;
import com.backend.cinejungla.persistence.entity.Cliente;

import java.util.Optional;

public class ClienteRepository {

    private ClienteCrudRepository clienteCrudRepository;

    public Optional<Cliente> getByCedula(int cedulaCliente){
        return clienteCrudRepository.getByCedula(cedulaCliente);
    }

    public Optional<Cliente> inicioSesion(String usuarioCliente, String passwordCliente){
        return (Optional<Cliente>) clienteCrudRepository.inicioSesion(usuarioCliente, passwordCliente);
    }
}
