package com.backend.cinejungla.web.procesoCompra;

import java.io.Serializable;

public class SnackTM implements Serializable {

    private int codigoSnack;

    private String nombreSnack;

    private double precioUnitario;

    private int cantidadStock;

    private int cantidadComprada;

    private String direccionImagen;

    public SnackTM(int codigoSnack, String nombreSnack, double precioUnitario, int cantidadStock, int cantidadComprada){
        this.codigoSnack = codigoSnack;
        this.nombreSnack = nombreSnack;
        this.precioUnitario = precioUnitario;
        this.cantidadStock = cantidadStock;
        this.cantidadComprada = cantidadComprada;
    }

    public SnackTM(){}

    public int getCodigoSnack() {
        return codigoSnack;
    }

    public String getNombreSnack() {
        return nombreSnack;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public int getCantidadComprada() {
        return cantidadComprada;
    }

    public void setCodigoSnack(int codigoSnack) {
        this.codigoSnack = codigoSnack;
    }

    public void setNombreSnack(String nombreSnack) {
        this.nombreSnack = nombreSnack;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public void setCantidadComprada(int cantidadComprada) {
        this.cantidadComprada = cantidadComprada;
    }

    public String getDireccionImagen() { return direccionImagen; }

    public void setDireccionImagen(String direccionImagen) { this.direccionImagen = direccionImagen; }
}
