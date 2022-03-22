package com.backend.cinejungla.persistence.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name = "venta_snack")
public class VentaSnack {

    @EmbeddedId
    private VentaSnackPK id;

    @Column(name = "stock_actual")
    private Integer stockActual;
}
