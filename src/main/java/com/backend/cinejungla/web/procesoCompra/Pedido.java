package com.backend.cinejungla.web.procesoCompra;

import com.backend.cinejungla.persistence.crud.FuncionCrudRepository;
import com.backend.cinejungla.persistence.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;


public class Pedido implements Serializable {

    private Cliente cliente;

    //Dejarlo como codigo
    private Multiplex multiplex;

    private Date fechaFuncion;

    //Dejarla como codigo
    private Pelicula pelicula;

    //Esto no es necesario
    private List<Funcion> funciones;

    private Funcion funcionEscogida;

    private String tipoSilla;

    //Creo que esto no es necesario
    private List<Silla> listadoSillas;

    private List<SillaTM> listadoSillasTM;

    //Esto no es necesario
    private List<SillaTM> sillasEscogidas;

    private List<SnackTM> snacksComprados;

    private FacturaCompraTM facturaCompraTM;


    public Pedido(){}

    public Cliente getCliente() {
        return cliente;
    }

    public Multiplex getMultiplex() {
        return multiplex;
    }

    public Date getFechaFuncion() {
        return fechaFuncion;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public List<Funcion> getFunciones() {
        return funciones;
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

    public List<SillaTM> getSillasEscogidas() {
        return sillasEscogidas;
    }

    public List<SnackTM> getSnacksComprados() {
        return snacksComprados;
    }

    public FacturaCompraTM getFacturaCompraTM() {
        return facturaCompraTM;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setMultiplex(Multiplex multiplex) {
        this.multiplex = multiplex;
    }

    public void setFechaFuncion(Date fechaFuncion) {
        this.fechaFuncion = fechaFuncion;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public void setFunciones(List<Funcion> funciones) {
        this.funciones = funciones;
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

    public void setSillasEscogidas(List<SillaTM> sillasEscogidas) {
        this.sillasEscogidas = sillasEscogidas;
    }

    public void setSnacksComprados(List<SnackTM> snacksComprados) {
        this.snacksComprados = snacksComprados;
    }

    public void setFacturaCompraTM(FacturaCompraTM facturaCompraTM) {
        this.facturaCompraTM = facturaCompraTM;
    }
}
