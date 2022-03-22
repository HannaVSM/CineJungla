package com.backend.cinejungla.persistence.entity;

import javax.persistence.*;

@Entity
@Table (name= "pelicula")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_pelicula")
    private Integer codigoPelicula;

    @Column(name ="nombre_pelicula")
    private String nombrePelicula;

    @Column (name = "descripcion_pelicula")
    private String descripcionPelicula;

    @Column (name = "duracion_pelicula")
    private Integer duracionPelicula;

    @Column (name = "direccion_portada")
    private String direccionPortada;

}
