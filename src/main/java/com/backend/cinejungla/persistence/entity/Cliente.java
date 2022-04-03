package com.backend.cinejungla.persistence.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="cliente")
public class Cliente implements Serializable {

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

    @OneToMany(mappedBy = "cliente")
    private List<FacturaCompra> facturaCompras;

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
