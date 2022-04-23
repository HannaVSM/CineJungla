package com.backend.cinejungla.web.procesoCompra;


import java.io.Serializable;


public class Pedido implements Serializable {

    private int codigoPedido;

    private int codigoMultiplex;

    private int codigoPelicula;

    private int codigoFuncion;

    private String tipoSilla;

    private static FacturaCompraTM facturaCompraTM;

    public Pedido(){}

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public int getCodigoMultiplex() {
        return codigoMultiplex;
    }

    public int getCodigoPelicula() {
        return codigoPelicula;
    }

    public int getCodigoFuncion() {
        return codigoFuncion;
    }

    public String getTipoSilla() {
        return tipoSilla;
    }

    public static FacturaCompraTM getFacturaCompraTM() {
        return facturaCompraTM;
    }

    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public void setCodigoMultiplex(int codigoMultiplex) {
        this.codigoMultiplex = codigoMultiplex;
    }

    public void setCodigoPelicula(int codigoPelicula) {
        this.codigoPelicula = codigoPelicula;
    }

    public void setCodigoFuncion(int codigoFuncion) {
        this.codigoFuncion = codigoFuncion;
    }

    public void setTipoSilla(String tipoSilla) {
        this.tipoSilla = tipoSilla;
    }

    public static void setFacturaCompraTM(FacturaCompraTM facturaCompraTM) {
        Pedido.facturaCompraTM = facturaCompraTM;
    }
}
