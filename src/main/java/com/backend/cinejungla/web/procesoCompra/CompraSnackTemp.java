package com.backend.cinejungla.web.procesoCompra;

public class CompraSnackTemp {

    private int codigoSnack;

    private int codigoPedido;

    private int cantidadSnackComprado;

    public CompraSnackTemp(){}

    public int getCodigoSnack() {
        return codigoSnack;
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public int getCantidadSnackComprado() {
        return cantidadSnackComprado;
    }

    public void setCodigoSnack(int codigoSnack) {
        this.codigoSnack = codigoSnack;
    }

    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public void setCantidadSnackComprado(int cantidadSnackComprado) {
        this.cantidadSnackComprado = cantidadSnackComprado;
    }
}
