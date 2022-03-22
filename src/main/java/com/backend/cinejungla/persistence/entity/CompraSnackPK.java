package com.backend.cinejungla.persistence.entity;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CompraSnackPK implements Serializable {

    @Column(name = "codigo_snack")
    private Integer codigoSnack;

    @Column(name = "codigo_factura_compra")
    private Integer codigoFacturaCompra;

    public Integer getCodigoSnack() {
        return codigoSnack;
    }

    public void setCodigoSnack(Integer codigoSnack) {
        this.codigoSnack = codigoSnack;
    }

    public Integer getCodigoFacturaCompra() {
        return codigoFacturaCompra;
    }

    public void setCodigoFacturaCompra(Integer codigoFacturaCompra) {
        this.codigoFacturaCompra = codigoFacturaCompra;
    }
}
