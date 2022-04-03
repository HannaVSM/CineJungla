package com.backend.cinejungla.persistence.crud;

import com.backend.cinejungla.persistence.entity.DetalleDispoSilla;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface DetalleDispoSillaCrudRepository extends CrudRepository <DetalleDispoSilla, Integer> {
    //Query Method
    List <DetalleDispoSilla> findByDisponibilidadSillaEquals(boolean disponibilidadSilla);

    @Query(value = "SELECT * FROM detalle_dispo_silla WHERE codigo_factura_compra = ?", nativeQuery = true)
    List<DetalleDispoSilla> getDetalleDispoSillaFactura(int codigoFactura);

    @Query(value = "SELECT * FROM detalle_dispo_silla WHERE codigo_silla = ? AND codigo_funcion = ?", nativeQuery = true)
    Optional<DetalleDispoSilla> getDetalleByCodigoSillaAndFuncion(int codigoSilla, int codigoFuncion);

    @Modifying
    @Query(value = "INSERT INTO detalle_dispo_silla(codigo_silla, codigo_funcion, codigo_factura_compra, disponibilidad_silla) VALUES (:codigoSilla, :codigoFuncion, :codigoFacturaCompra, :disponibilidadSilla)", nativeQuery = true)
    @Transactional
    void registrarFactura(@Param("codigoSilla") int codigoSilla, @Param("codigoFuncion") int codigoFuncion, @Param("codigoFacturaCompra") int codigoFacturaCompra, @Param("disponibilidadSilla") boolean disponibilidadSilla);
}
