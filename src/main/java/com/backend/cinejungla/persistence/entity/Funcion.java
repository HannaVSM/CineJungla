package com.backend.cinejungla.persistence.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "funcion")
public class Funcion {

  @Id
  @GeneratedValue ( strategy = GenerationType.IDENTITY)
  @Column (name = "codigo_funcion")
  private Integer codigoFuncion;

  @Column(name = "fecha_funcion")
  private Date fechaFuncion;

  @Column (name = "hora_funcion")
  private String horaFuncion;

  @Column(name = "codigo_pelicula")
  private Integer codigoPelicula;

  @Column(name = "codigo_sala")
    private  Integer codigoSala;

  @Column(name = "disponibilidad_funcion")
  private Boolean disponibilidadFuncion;

  @Column(name = "tipo_funcion")
  private  String tipoFuncion;

  private Boolean doblaje;

  public Integer getCodigoFuncion() {
    return codigoFuncion;
  }

  public void setCodigoFuncion(Integer codigoFuncion) {
    this.codigoFuncion = codigoFuncion;
  }

  public Date getFechaFuncion() {
    return fechaFuncion;
  }

  public String getHoraFuncion() {
    return horaFuncion;
  }

  public void setHoraFuncion(String horaFuncion) {
    this.horaFuncion = horaFuncion;
  }

  public void setFechaFuncion(Date fechaFuncion) {
    this.fechaFuncion = fechaFuncion;
  }

  public Integer getCodigoPelicula() {
    return codigoPelicula;
  }

  public void setCodigoPelicula(Integer codigoPelicula) {
    this.codigoPelicula = codigoPelicula;
  }

  public Integer getCodigoSala() {
    return codigoSala;
  }

  public void setCodigoSala(Integer codigoSala) {
    this.codigoSala = codigoSala;
  }

  public Boolean getDisponibilidadFuncion() {
    return disponibilidadFuncion;
  }

  public void setDisponibilidadFuncion(Boolean disponibilidadFuncion) {
    this.disponibilidadFuncion = disponibilidadFuncion;
  }

  public String getTipoFuncion() {
    return tipoFuncion;
  }

  public void setTipoFuncion(String tipoFuncion) {
    this.tipoFuncion = tipoFuncion;
  }

  public Boolean getDoblaje() {
    return doblaje;
  }

  public void setDoblaje(Boolean doblaje) {
    this.doblaje = doblaje;
  }
}
