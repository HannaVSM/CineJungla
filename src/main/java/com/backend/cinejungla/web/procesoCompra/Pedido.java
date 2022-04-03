package com.backend.cinejungla.web.procesoCompra;


import com.backend.cinejungla.persistence.entity.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;


public class Pedido implements Serializable {

    private int codigoMultiplex;

    private Date fechaFuncion;

    private int codigoPelicula;

    private Funcion funcionEscogida;

    private String tipoSilla;

    private List<Silla> listadoSillas;

    private List<SillaTM> listadoSillasTM;

    private List<SnackTM> snacksComprados;

    private FacturaCompraTM facturaCompraTM;


    public Pedido(){}

    public int getCodigoMultiplex() {
        return codigoMultiplex;
    }

    public Date getFechaFuncion() {
        return fechaFuncion;
    }

    public int getCodigoPelicula() {
        return codigoPelicula;
    }

    public Funcion getFuncionEscogida() {
        return funcionEscogida;
    }

    public String getTipoSilla() {
        return tipoSilla;
    }

    public List<Silla> getListadoSillas() {
        return listadoSillas;
    }

    public List<SillaTM> getListadoSillasTM() {
        return listadoSillasTM;
    }

    public List<SnackTM> getSnacksComprados() {
        return snacksComprados;
    }

    public FacturaCompraTM getFacturaCompraTM() {
        return facturaCompraTM;
    }

    public void setCodigoMultiplex(int codigoMultiplex) {
        this.codigoMultiplex = codigoMultiplex;
    }

    public void setFechaFuncion(Date fechaFuncion) {
        this.fechaFuncion = fechaFuncion;
    }

    public void setCodigoPelicula(int codigoPelicula) {
        this.codigoPelicula = codigoPelicula;
    }

    public void setFuncionEscogida(Funcion funcionEscogida) {
        this.funcionEscogida = funcionEscogida;
    }

    public void setTipoSilla(String tipoSilla) {
        this.tipoSilla = tipoSilla;
    }

    public void setListadoSillas(List<Silla> listadoSillas) {
        this.listadoSillas = listadoSillas;
    }

    public void setListadoSillasTM(List<SillaTM> listadoSillasTM) {
        this.listadoSillasTM = listadoSillasTM;
    }

    public void setSnacksComprados(List<SnackTM> snacksComprados) {
        this.snacksComprados = snacksComprados;
    }

    public void setFacturaCompraTM(FacturaCompraTM facturaCompraTM) {
        this.facturaCompraTM = facturaCompraTM;
    }
}
