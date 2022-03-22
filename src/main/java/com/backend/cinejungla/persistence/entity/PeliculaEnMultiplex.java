package com.backend.cinejungla.persistence.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pelicula_en_multiplex")
public class PeliculaEnMultiplex {

    @EmbeddedId
    private PeliculaEnMultiplexPK id;

    @Column(name = "pelicula_en_cartelera")
    private Boolean peliculaEnCartelera;
}
