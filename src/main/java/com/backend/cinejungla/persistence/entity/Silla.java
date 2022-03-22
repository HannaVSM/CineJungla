package com.backend.cinejungla.persistence.entity;

import javax.persistence.*;

@Entity
@Table (name = "silla")
public class Silla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_silla")
    private Integer codigoSilla;

    @Column(name = "ubicacion_silla")
    private  String ubicacionSilla;

    @Column(name = "tipo_silla")
    private String tipoSilla;

    @Column(name = "precio_silla")
    private Integer precioSilla;

    @Column(name = "codigo_sala")
    private Integer codigoSala;

    }
