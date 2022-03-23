package com.backend.cinejungla.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "pelicula_en_multiplex")
public class PeliculaEnMultiplex {

    @EmbeddedId
    private PeliculaEnMultiplexPK id;

    @Column(name = "pelicula_en_cartelera")
    private Boolean peliculaEnCartelera;

    //No lo veo necesario
    @ManyToOne
    @JoinColumn(name = "codigo_multiplex", insertable = false, updatable = false)
    private Multiplex multiplex;

    @ManyToOne
    @JoinColumn(name = "codigo_pelicula", insertable = false, updatable = false)
    private Pelicula pelicula;

    public PeliculaEnMultiplexPK getId() {
        return id;
    }

    public void setId(PeliculaEnMultiplexPK id) {
        this.id = id;
    }

    public Boolean getPeliculaEnCartelera() {
        return peliculaEnCartelera;
    }

    public void setPeliculaEnCartelera(Boolean peliculaEnCartelera) {
        this.peliculaEnCartelera = peliculaEnCartelera;
    }
}
