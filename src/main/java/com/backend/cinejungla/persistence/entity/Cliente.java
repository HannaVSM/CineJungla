package com.backend.cinejungla.persistence.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="cliente")
public class Cliente {

    @Id
    @Column (name = "cedula_cliente")
    private Integer cedulaCliente;

    @Column (name = "nombre_cliente")
    private String nombreCliente;

    @Column (name = "usuario_cliente")
    private String usuarioCliente;

    @Column(name = "password_cliente")
    private String passwordCliente;

    private Integer puntos;

    @Column(name = "fecha_caducidad_puntos")
    private Date fechaCaducidadPuntos;

    public Integer getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(Integer cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getUsuarioCliente() {
        return usuarioCliente;
    }

    public void setUsuarioCliente(String usuarioCliente) {
        this.usuarioCliente = usuarioCliente;
    }

    public String getPasswordCliente() {
        return passwordCliente;
    }

    public void setPasswordCliente(String passwordCliente) {
        this.passwordCliente = passwordCliente;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public Date getFechaCaducidadPuntos() {
        return fechaCaducidadPuntos;
    }

    public void setFechaCaducidadPuntos(Date fechaCaducidadPuntos) {
        this.fechaCaducidadPuntos = fechaCaducidadPuntos;
    }
}
