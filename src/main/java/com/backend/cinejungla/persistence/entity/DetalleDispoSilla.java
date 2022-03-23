package com.backend.cinejungla.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "detalle_dispo_silla")
public class DetalleDispoSilla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "codigo_det_disp_silla")
    private Integer codigoDetDispSilla;

    @Column(name = "codigo_silla")
    private Integer codigoSilla;

    @Column(name = "codigo_funcion")
    private Integer codigoFuncion;

    @Column (name = "codigo_factura_compra")
    private Integer codigoFacturaCompra;

    @Column (name = "disponibilidad_silla")
    private Boolean disponibilidadSilla;

    @ManyToOne
    @JoinColumn(name = "codigo_funcion", insertable = false, updatable = false)
    private Funcion funcion;

    @ManyToOne
    @JoinColumn(name = "codigo_factura_compra", insertable = false, updatable = false)
    private FacturaCompra facturaCompra;

    @ManyToOne
    @JoinColumn(name = "codigo_silla", insertable = false, updatable = false)
    private Silla silla;

    public Integer getCodigoDetDispSilla() {
        return codigoDetDispSilla;
    }

    public void setCodigoDetDispSilla(Integer codigoDetDispSilla) {
        this.codigoDetDispSilla = codigoDetDispSilla;
    }

    public Integer getCodigoSilla() {
        return codigoSilla;
    }

    public void setCodigoSilla(Integer codigoSilla) {
        this.codigoSilla = codigoSilla;
    }

    public Integer getCodigoFuncion() {
        return codigoFuncion;
    }

    public void setCodigoFuncion(Integer codigoFuncion) {
        this.codigoFuncion = codigoFuncion;
    }

    public Integer getCodigoFacturaCompra() {
        return codigoFacturaCompra;
    }

    public void setCodigoFacturaCompra(Integer codigoFacturaCompra) {
        this.codigoFacturaCompra = codigoFacturaCompra;
    }

    public Boolean getDisponibilidadSilla() {
        return disponibilidadSilla;
    }

    public void setDisponibilidadSilla(Boolean disponibilidadSilla) {
        this.disponibilidadSilla = disponibilidadSilla;
    }
}
