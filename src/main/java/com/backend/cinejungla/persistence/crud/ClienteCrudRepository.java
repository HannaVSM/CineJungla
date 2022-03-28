package com.backend.cinejungla.persistence.crud;

import com.backend.cinejungla.persistence.entity.Cliente;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.Optional;

public interface ClienteCrudRepository extends CrudRepository<Cliente, Integer> {

    @Query(value ="SELECT * FROM cliente WHERE cedula_cliente = ?", nativeQuery = true)
    Optional<Cliente> getByCedula(int cedulaCliente);

    @Query(value ="SELECT usuario_cliente, password_cliente FROM cliente WHERE usuario_cliente = ? AND password_cliente = ?", nativeQuery = true)
    Optional<Cliente> inicioSesion(String usuarioCliente, String passwordCliente);

    @Modifying
    @Query(value = "UPDATE cliente SET puntos = :puntos WHERE cedula_cliente = :cedulaCliente", nativeQuery = true)
    @Transactional
    void actualizarPuntos(@Param("puntos") int puntos, @Param("cedulaCliente") int cedulaCliente);

    @Modifying
    @Query(value = "UPDATE cliente SET puntos = :puntos, fecha_caducidad_puntos = :fechaCaducidadPuntos WHERE cedula_cliente = :cedulaCliente", nativeQuery = true)
    @Transactional
    void actualizarPuntosAndFecha(@Param("puntos") int puntos, @Param("fechaCaducidadPuntos") Date fechaCaducidadPuntos, @Param("cedulaCliente") int cedulaCliente);

}
