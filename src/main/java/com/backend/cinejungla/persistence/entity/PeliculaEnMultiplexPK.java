package com.backend.cinejungla.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PeliculaEnMultiplexPK implements Serializable
{
    @Column(name = "codigo_multiplex")
    private Integer codigoMultiplex;

    @Column(name ="codigo_pelicula")
    private Integer codigoPelicula;

    public Integer getCodigoMultiplex() {
        return codigoMultiplex;
    }

    public void setCodigoMultiplex(Integer codigoMultiplex) {
        this.codigoMultiplex = codigoMultiplex;
    }

    public Integer getCodigoPelicula() {
        return codigoPelicula;
    }

    public void setCodigoPelicula(Integer codigoPelicula) {
        this.codigoPelicula = codigoPelicula;
    }
}
