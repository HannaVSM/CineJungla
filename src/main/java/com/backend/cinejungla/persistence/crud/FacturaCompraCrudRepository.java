package com.backend.cinejungla.persistence.crud;

import com.backend.cinejungla.persistence.entity.FacturaCompra;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FacturaCompraCrudRepository extends CrudRepository <FacturaCompra, Integer> {

    @Query(value = "SELECT * FROM factura_compra WHERE cedula_cliente =?", nativeQuery = true)
    List<FacturaCompra> getFacturasCliente(int cedulaCliente);
}
