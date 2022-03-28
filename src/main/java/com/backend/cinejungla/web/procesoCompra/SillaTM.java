package com.backend.cinejungla.web.procesoCompra;

import java.io.Serializable;

public class SillaTM implements Serializable {

    private int codigoSilla;

    private String ubicacionSilla;

    private String tipoSilla;

    private int precioSilla;

    private int codigoSala;

    private int codigoFuncion;

    private boolean disponibilidadSilla;

    public SillaTM(int codigoSilla, String ubicacionSilla, String tipoSilla, int precioSilla, int codigoSala, int codigoFuncion, boolean disponibilidadSilla){
        this.codigoSilla = codigoSilla;
        this.ubicacionSilla = ubicacionSilla;
        this.tipoSilla = tipoSilla;
        this.precioSilla = precioSilla;
        this.codigoSala = codigoSala;
        this.codigoFuncion = codigoFuncion;
        this.disponibilidadSilla = disponibilidadSilla;
    }

    public SillaTM(){}

    public int getCodigoSilla() {
        return codigoSilla;
    }

    public String getUbicacionSilla() {
        return ubicacionSilla;
    }

    public String getTipoSilla() {
        return tipoSilla;
    }

    public int getPrecioSilla() {
        return precioSilla;
    }

    public int getCodigoSala() {
        return codigoSala;
    }

    public int getCodigoFuncion() {
        return codigoFuncion;
    }

    public boolean isDisponibilidadSilla() {
        return disponibilidadSilla;
    }

    public void setCodigoSilla(int codigoSilla) {
        this.codigoSilla = codigoSilla;
    }

    public void setUbicacionSilla(String ubicacionSilla) {
        this.ubicacionSilla = ubicacionSilla;
    }

    public void setTipoSilla(String tipoSilla) {
        this.tipoSilla = tipoSilla;
    }

    public void setPrecioSilla(int precioSilla) {
        this.precioSilla = precioSilla;
    }

    public void setCodigoSala(int codigoSala) {
        this.codigoSala = codigoSala;
    }

    public void setCodigoFuncion(int codigoFuncion) {
        this.codigoFuncion = codigoFuncion;
    }

    public void setDisponibilidadSilla(boolean disponibilidadSilla) {
        this.disponibilidadSilla = disponibilidadSilla;
    }
}
