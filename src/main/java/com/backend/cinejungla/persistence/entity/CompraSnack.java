package com.backend.cinejungla.persistence.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "compra_snack")
public class CompraSnack {

    @EmbeddedId
    private CompraSnackPK id;

    @Column(name = "cantidad_snack_comprado")
    private Integer cantidadSnackComprado;
}
