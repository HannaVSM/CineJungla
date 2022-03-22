package com.backend.cinejungla.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "multiplex")
public class Multiplex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "codigo_multiplex")
    private Integer codigoMultiplex;

    @Column (name = "nombre_multiplex")
    private String nombreMultiplex;

}
