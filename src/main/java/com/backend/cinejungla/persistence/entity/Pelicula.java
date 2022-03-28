package com.backend.cinejungla.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table (name= "pelicula")
public class Pelicula implements Serializable {

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

    @OneToMany(mappedBy = "pelicula")
    private List <PeliculaEnMultiplex> peliculaEnMultiplexes;

    @OneToMany(mappedBy = "pelicula")
    private List <Funcion> funciones;

    public Integer getCodigoPelicula() {
        return codigoPelicula;
    }

    public void setCodigoPelicula(Integer codigoPelicula) {
        this.codigoPelicula = codigoPelicula;
    }

    public String getNombrePelicula() {
        return nombrePelicula;
    }

    public void setNombrePelicula(String nombrePelicula) {
        this.nombrePelicula = nombrePelicula;
    }

    public String getDescripcionPelicula() {
        return descripcionPelicula;
    }

    public void setDescripcionPelicula(String descripcionPelicula) {
        this.descripcionPelicula = descripcionPelicula;
    }

    public Integer getDuracionPelicula() {
        return duracionPelicula;
    }

    public void setDuracionPelicula(Integer duracionPelicula) {
        this.duracionPelicula = duracionPelicula;
    }

    public String getDireccionPortada() {
        return direccionPortada;
    }

    public void setDireccionPortada(String direccionPortada) {
        this.direccionPortada = direccionPortada;
    }
}
