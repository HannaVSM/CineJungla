package com.backend.cinejungla.web.procesoCompra;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FacturaCompraTM implements Serializable {

    private int cedulaCliente;

    private String nombreCliente;

    private Date fechaFactura;

    private String multiplex;

    private String pelicula;

    private Date fechaFuncion;

    private LocalDateTime horaFuncion;

    private String tipoFuncion;

    private boolean doblada;

    private String nombreSala;

    private List<String> sillas = new ArrayList<String>();

    private int precioTotalSillas;

    private List<SnackTM> snacksComprados = new ArrayList<SnackTM>();

    private double precioTotalSnacks;

    private boolean puntosRedimidos;

    private double totalPago;


    public FacturaCompraTM(){}

    public String getNombreCliente() {
        return nombreCliente;
    }

    public int getCedulaCliente() {
        return cedulaCliente;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public String getMultiplex() {
        return multiplex;
    }

    public String getPelicula() {
        return pelicula;
    }

    public Date getFechaFuncion() {
        return fechaFuncion;
    }

    public LocalDateTime getHoraFuncion() {
        return horaFuncion;
    }

    public String getTipoFuncion() {
        return tipoFuncion;
    }

    public boolean isDoblada() {
        return doblada;
    }

    public String getNombreSala() {
        return nombreSala;
    }

    public List<String> getSillas() {
        return sillas;
    }

    public int getPrecioSillas() {
        return precioTotalSillas;
    }

    public List<SnackTM> getSnacksComrpados() {
        return snacksComprados;
    }

    public double getPrecioSnacks() {
        return precioTotalSnacks;
    }

    public boolean isPuntosRedimidos() {
        return puntosRedimidos;
    }

    public double getTotalPago() {
        return totalPago;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setCedulaCliente(int cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public void setMultiplex(String multiplex) {
        this.multiplex = multiplex;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    public void setFechaFuncion(Date fechaFuncion) {
        this.fechaFuncion = fechaFuncion;
    }

    public void setHoraFuncion(LocalDateTime horaFuncion) {
        this.horaFuncion = horaFuncion;
    }

    public void setTipoFuncion(String tipoFuncion) {
        this.tipoFuncion = tipoFuncion;
    }

    public void setDoblada(boolean doblada) {
        this.doblada = doblada;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }

    public void setSillas(List<String> sillas) {
        this.sillas = sillas;
    }

    public void setPrecioSillas(int precioTotalSillas) {
        this.precioTotalSillas = precioTotalSillas;
    }

    public void setSnacksComprados(List<SnackTM> snacksComrpados) {
        this.snacksComprados = snacksComrpados;
    }

    public void setPrecioSnacks(double precioTotalSnacks) {
        this.precioTotalSnacks = precioTotalSnacks;
    }

    public void setPuntosRedimidos(boolean puntosRedimidos) {
        this.puntosRedimidos = puntosRedimidos;
    }

    public void setTotalPago(double totalPago) {
        this.totalPago = totalPago;
    }
}
