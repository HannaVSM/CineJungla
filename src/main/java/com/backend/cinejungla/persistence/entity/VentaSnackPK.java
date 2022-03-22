package com.backend.cinejungla.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class VentaSnackPK implements Serializable {

    @Column(name = "codigo_multiplex")
    private Integer codigoMultiplex;

    @Column(name = "codigo_snack")
    private Integer codigoSnack;

    public Integer getCodigoMultiplex() {
        return codigoMultiplex;
    }

    public void setCodigoMultiplex(Integer codigoMultiplex) {
        this.codigoMultiplex = codigoMultiplex;
    }

    public Integer getCodigoSnack() {
        return codigoSnack;
    }

    public void setCodigoSnack(Integer codigoSnack) {
        this.codigoSnack = codigoSnack;
    }
}
