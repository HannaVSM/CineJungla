package com.backend.cinejungla.persistence.entity;

import javax.persistence.*;

@Entity
@Table (name = "snack")
public class Snack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "codigo_snack")
    private Integer codigoSnack;

    @Column (name = "nombre_snack")
    private String nombreSnack;

    @Column (name = "precio_unitario")
    private Double precioUnitario;
}
