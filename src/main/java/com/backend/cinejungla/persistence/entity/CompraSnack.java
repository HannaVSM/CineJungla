package com.backend.cinejungla.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "compra_snack")
public class CompraSnack {

    @EmbeddedId
    private CompraSnackPK id;

    @Column(name = "cantidad_snack_comprado")
    private Integer cantidadSnackComprado;

    @ManyToOne
    @JoinColumn(name = "codigo_factura_compra", insertable = false, updatable = false)
    private FacturaCompra facturaCompra;

    @ManyToOne
    @JoinColumn(name = "codigo_snack", insertable = false, updatable = false)
    private Snack snack;

    public CompraSnackPK getId() {
        return id;
    }

    public void setId(CompraSnackPK id) {
        this.id = id;
    }

    public Integer getCantidadSnackComprado() {
        return cantidadSnackComprado;
    }

    public void setCantidadSnackComprado(Integer cantidadSnackComprado) {
        this.cantidadSnackComprado = cantidadSnackComprado;
    }
}
