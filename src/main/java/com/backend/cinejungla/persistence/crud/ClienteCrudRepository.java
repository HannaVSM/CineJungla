package com.backend.cinejungla.persistence.crud;

import com.backend.cinejungla.persistence.entity.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClienteCrudRepository extends CrudRepository<Cliente, Integer> {

    @Query(value ="SELECT * FROM cliente WHERE cedula_cliente = ?", nativeQuery = true)
    Optional<Cliente> getByCedula(int cedulaCliente);

    @Query(value ="SELECT usuario_cliente, password_cliente FROM cliente WHERE usuario_cliente = ? AND password_cliente = ?", nativeQuery = true)
    Optional<Cliente> inicioSesion(String usuarioCliente, String passwordCliente);

}
