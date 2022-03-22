package com.backend.cinejungla.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sala")
public class Sala {

    @Id
    @Column (name = "codigo_sala")
    private Integer codigoSala;

    @Column (name = "nombre_sala")
    private String nombreSala;

    @Column (name = "codigo_multiplex")
    private Integer codigoMultiplex;

    public Integer getCodigoSala() {
        return codigoSala;
    }

    public void setCodigoSala(Integer codigoSala) {
        this.codigoSala = codigoSala;
    }

    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }

    public Integer getCodigoMultiplex() {
        return codigoMultiplex;
    }

    public void setCodigoMultiplex(Integer codigoMultiplex) {
        this.codigoMultiplex = codigoMultiplex;
    }
}
