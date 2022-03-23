package com.backend.cinejungla.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "multiplex")
public class Multiplex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "codigo_multiplex")
    private Integer codigoMultiplex;

    @Column (name = "nombre_multiplex")
    private String nombreMultiplex;

    @OneToMany(mappedBy = "multiplex")
    private List <Empleado> empleados;

    @OneToMany(mappedBy = "multiplex")
    private List <PeliculaEnMultiplex> peliculaEnMultiplexes;

    @OneToMany(mappedBy = "multiplex")
    private List <VentaSnack> ventaSnacks;

    @OneToMany(mappedBy = "multiplex")
    private List <Sala> salas;

    public Integer getCodigoMultiplex() {
        return codigoMultiplex;
    }

    public void setCodigoMultiplex(Integer codigoMultiplex) {
        this.codigoMultiplex = codigoMultiplex;
    }

    public String getNombreMultiplex() {
        return nombreMultiplex;
    }

    public void setNombreMultiplex(String nombreMultiplex) {
        this.nombreMultiplex = nombreMultiplex;
    }
}
