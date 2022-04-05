package com.backend.cinejungla.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "funcion")
public class Funcion implements Serializable {

  @Id
  @GeneratedValue ( strategy = GenerationType.IDENTITY)
  @Column (name = "codigo_funcion")
  private Integer codigoFuncion;

  @Column(name = "fecha_funcion")
  private Date fechaFuncion;

  @Column (name = "hora_funcion")
  private LocalDateTime horaFuncion;

  @Column(name = "codigo_pelicula")
  private Integer codigoPelicula;

  @Column(name = "codigo_sala")
    private  Integer codigoSala;

  @Column(name = "disponibilidad_funcion")
  private Boolean disponibilidadFuncion;

  @Column(name = "tipo_funcion")
  private  String tipoFuncion;

  private Boolean doblaje;

  @ManyToOne
  @JoinColumn(name = "codigo_pelicula", insertable = false, updatable = false)
  private Pelicula pelicula;

  @ManyToOne
  @JoinColumn(name = "codigo_sala", insertable = false, updatable = false)
  private Sala sala;

  @OneToMany(mappedBy = "funcion")
  private List<DetalleDispoSilla> detalleDispoSillas;

  public Integer getCodigoFuncion() {
    return codigoFuncion;
  }

  public void setCodigoFuncion(Integer codigoFuncion) {
    this.codigoFuncion = codigoFuncion;
  }

  public Date getFechaFuncion() {
    return fechaFuncion;
  }

  public LocalDateTime getHoraFuncion() {
    return horaFuncion;
  }

  public void setHoraFuncion(LocalDateTime horaFuncion) {
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
