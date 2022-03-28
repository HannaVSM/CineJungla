package com.backend.cinejungla.persistence.crud;

import com.backend.cinejungla.persistence.entity.FacturaCompra;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface FacturaCompraCrudRepository extends CrudRepository <FacturaCompra, Integer> {

    @Query(value = "SELECT * FROM factura_compra", nativeQuery = true)
    List<FacturaCompra> getAll();

    @Query(value = "SELECT * FROM factura_compra WHERE cedula_cliente =?", nativeQuery = true)
    List<FacturaCompra> getFacturasCliente(int cedulaCliente);

    @Query(value = "SELECT * FROM factura_compra WHERE cedula_cliente = ? AND fecha_factura = ?", nativeQuery = true)
    Optional<FacturaCompra> getFacturaByClienteAndFecha(int cedulaCliente, Date fechaFactura);

    @Modifying
    @Query(value = "INSERT INTO factura_compra(puntos_redimidos, total_pago, cedula_cliente, fecha_factura) VALUES (:puntosRedimidos, :totalPago, :cedulaCliente, :fechaFactura)", nativeQuery = true)
    @Transactional
    void registrarFactura(@Param("puntosRedimidos") boolean puntosRedimidos, @Param("totalPago") double totalPago, @Param("cedulaCliente") int cedulaCliente, @Param("fechaFactura") Date fechaFactura);
}
