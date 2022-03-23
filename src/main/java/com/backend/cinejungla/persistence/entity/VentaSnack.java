package com.backend.cinejungla.persistence.entity;

import javax.persistence.*;

@Entity
@Table (name = "venta_snack")
public class VentaSnack {

    @EmbeddedId
    private VentaSnackPK id;

    @Column(name = "stock_actual")
    private Integer stockActual;

    //No lo veo necesario
    @ManyToOne
    @JoinColumn(name = "codigo_multiplex", insertable = false, updatable = false)
    private Multiplex multiplex;

    @ManyToOne
    @JoinColumn(name = "codigo_snack", insertable = false, updatable = false)
    private Snack snack;

    public VentaSnackPK getId() {
        return id;
    }

    public void setId(VentaSnackPK id) {
        this.id = id;
    }

    public Integer getStockActual() {
        return stockActual;
    }

    public void setStockActual(Integer stockActual) {
        this.stockActual = stockActual;
    }
}
