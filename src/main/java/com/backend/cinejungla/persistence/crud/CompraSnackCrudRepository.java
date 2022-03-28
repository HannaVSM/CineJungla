package com.backend.cinejungla.persistence.crud;

import com.backend.cinejungla.persistence.entity.CompraSnack;
import com.backend.cinejungla.persistence.entity.CompraSnackPK;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface CompraSnackCrudRepository extends CrudRepository <CompraSnack, CompraSnackPK> {

    @Modifying
    @Query(value = "INSERT INTO compra_snack(codigo_snack, codigo_factura_compra, cantidad_snack_comprado) VALUES (:codigoSnack, :codigoFacturaCompra, :cantidadSnackComprado)", nativeQuery = true)
    @Transactional
    void registrarFactura(@Param("codigoSnack") int codigoSnack, @Param("codigoFacturaCompra") int codigoFacturaCompra, @Param("cantidadSnackComprado") int cantidadSnackComprado);
}
