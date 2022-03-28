package com.backend.cinejungla.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "sala")
public class Sala implements Serializable {

    @Id
    @Column (name = "codigo_sala")
    private Integer codigoSala;

    @Column (name = "nombre_sala")
    private String nombreSala;

    @Column (name = "codigo_multiplex")
    private Integer codigoMultiplex;

    //No lo veo necesario
    @ManyToOne
    @JoinColumn(name = "codigo_multiplex", insertable = false, updatable = false)
    private Multiplex multiplex;

    @OneToMany (mappedBy = "sala")
    private List<Funcion> funciones;

    @OneToMany (mappedBy = "sala")
    private List<Silla> sillas;

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
