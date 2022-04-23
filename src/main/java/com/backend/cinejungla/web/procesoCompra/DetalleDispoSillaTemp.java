package com.backend.cinejungla.web.procesoCompra;

public class DetalleDispoSillaTemp {

    private int codigoSilla;

    private int codigoFuncion;

    private int codigoPedido;

    private boolean disponibilidadSilla;

    public DetalleDispoSillaTemp(){}

    public int getCodigoSilla() {
        return codigoSilla;
    }

    public int getCodigoFuncion() {
        return codigoFuncion;
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public boolean isDisponibilidadSilla() {
        return disponibilidadSilla;
    }

    public void setCodigoSilla(int codigoSilla) {
        this.codigoSilla = codigoSilla;
    }

    public void setCodigoFuncion(int codigoFuncion) {
        this.codigoFuncion = codigoFuncion;
    }

    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public void setDisponibilidadSilla(boolean disponibilidadSilla) {
        this.disponibilidadSilla = disponibilidadSilla;
    }
}
