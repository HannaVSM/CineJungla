package com.backend.cinejungla.domain.service;

import com.backend.cinejungla.persistence.crud.ClienteCrudRepository;
import com.backend.cinejungla.persistence.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteCrudRepository clienteCrudRepository;

    public Optional<Cliente> getByCedula(int cedulaCliente){
        return clienteCrudRepository.getByCedula(cedulaCliente);
    }

    public Optional<Cliente> inicioSesion(String usuarioCliente, String passwordCliente){
        return (Optional<Cliente>) clienteCrudRepository.inicioSesion(usuarioCliente, passwordCliente);
    }

    //No pasa por el controller
    public void actualizarPuntos(int puntos, int cedulaCliente){
        clienteCrudRepository.actualizarPuntos(puntos, cedulaCliente);
    }

    //No pasa por el controller
    public void actualizarPuntosAndFecha(int puntos, Date fechaCaducidadPuntos, int cedulaCliente){
        clienteCrudRepository.actualizarPuntosAndFecha(puntos, fechaCaducidadPuntos, cedulaCliente);
    }

    public void resetearPuntosAndFecha(int cedulaCliente){
        clienteCrudRepository.resetearPuntosAndFecha(cedulaCliente);
    }

    public void registrarCliente(Cliente cliente){
        clienteCrudRepository.save(cliente);
    }
}
