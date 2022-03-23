package com.backend.cinejungla.persistence;

import com.backend.cinejungla.persistence.crud.ClienteCrudRepository;
import com.backend.cinejungla.persistence.entity.Cliente;

import java.util.Optional;

public class ClienteRepository {

    private ClienteCrudRepository clienteCrudRepository;

    public Optional<Cliente> getById(int cedulaCliente){
        return clienteCrudRepository.findById(cedulaCliente);
    }
}
