package com.backend.cinejungla.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "snack")
public class Snack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "codigo_snack")
    private Integer codigoSnack;

    @Column (name = "nombre_snack")
    private String nombreSnack;

    @Column (name = "precio_unitario")
    private Double precioUnitario;

    @OneToMany(mappedBy = "snack")
    private List<CompraSnack> compraSnacks;

    @OneToMany(mappedBy = "snack")
    private List<VentaSnack> ventaSnacks;

    public Integer getCodigoSnack() {
        return codigoSnack;
    }

    public void setCodigoSnack(Integer codigoSnack) {
        this.codigoSnack = codigoSnack;
    }

    public String getNombreSnack() {
        return nombreSnack;
    }

    public void setNombreSnack(String nombreSnack) {
        this.nombreSnack = nombreSnack;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
