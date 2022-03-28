package com.backend.cinejungla.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table (name = "silla")
public class Silla implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_silla")
    private Integer codigoSilla;

    @Column(name = "ubicacion_silla")
    private  String ubicacionSilla;

    @Column(name = "tipo_silla")
    private String tipoSilla;

    @Column(name = "precio_silla")
    private Integer precioSilla;

    @Column(name = "codigo_sala")
    private Integer codigoSala;

    @ManyToOne
    @JoinColumn(name = "codigo_sala", insertable = false, updatable = false)
    private Sala sala;

    @OneToMany(mappedBy = "silla")
    private List<DetalleDispoSilla> detalleDispoSillas;

    public Integer getCodigoSilla() {
        return codigoSilla;
    }

    public void setCodigoSilla(Integer codigoSilla) {
        this.codigoSilla = codigoSilla;
    }

    public String getUbicacionSilla() {
        return ubicacionSilla;
    }

    public void setUbicacionSilla(String ubicacionSilla) {
        this.ubicacionSilla = ubicacionSilla;
    }

    public String getTipoSilla() {
        return tipoSilla;
    }

    public void setTipoSilla(String tipoSilla) {
        this.tipoSilla = tipoSilla;
    }

    public Integer getPrecioSilla() {
        return precioSilla;
    }

    public void setPrecioSilla(Integer precioSilla) {
        this.precioSilla = precioSilla;
    }

    public Integer getCodigoSala() {
        return codigoSala;
    }

    public void setCodigoSala(Integer codigoSala) {
        this.codigoSala = codigoSala;
    }
}
