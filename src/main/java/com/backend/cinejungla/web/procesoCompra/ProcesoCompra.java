package com.backend.cinejungla.web.procesoCompra;

import com.backend.cinejungla.persistence.entity.Funcion;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public abstract class ProcesoCompra implements Serializable {


    /*public Pedido serializacion(){

        Pedido pedido = new Pedido();

        guardarArchivo(pedido);
        pedido = leerArchivo();

        return pedido;
    }*/

    public List<Funcion> pruebaFunciones (int codigoMultiplex, int codigoPelicula, Date fechaFuncion){
        Pedido pedido = new Pedido();
        pedido = guardarInfoBasica(pedido, codigoMultiplex, codigoPelicula, fechaFuncion);
        pedido = funcionesPorPeliculaAndFecha(pedido);

        return (List<Funcion>)pedido.getFunciones();
    }

    //Se hacen los dos filtros para encontrar las funciones para una pelicula, en un multiplex, en un cierto dia
    public List<Funcion> consultarFunciones(int codigoMultiplex, int codigoPelicula, Date fechaFuncion){

        Pedido pedido = new Pedido();

        //Pedido pedido = leerArchivo();
        pedido = guardarInfoBasica(pedido, codigoMultiplex, codigoPelicula, fechaFuncion);
        pedido = funcionesPorPeliculaAndFecha(pedido);
        pedido = funcionesEnMultiplex(pedido);
        guardarArchivo(pedido);

        return (List<Funcion>)pedido.getFunciones();
    }

    /*public Pedido contultarFuncionesFiltroTipo(int codigoMultiplex, int codigoPelicula, Date fechaFuncion){

        Pedido pedido = new Pedido();

        //Pedido pedido = leerArchivo();
        pedido = consultarFunciones(codigoMultiplex, codigoPelicula, fechaFuncion);
        quintoPaso(pedido);

        return pedido;
    }

    public Pedido contultarFuncionesFiltroLenguaje(int codigoMultiplex, int codigoPelicula, Date fechaFuncion){
        Pedido pedido = new Pedido();

        //Pedido pedido = leerArchivo();
        pedido = consultarFunciones(codigoMultiplex, codigoPelicula, fechaFuncion);
        sextoPaso(pedido);

        return pedido;
    }*/


    //Se hacen los filtros  para encontrar las sillas generales o preferenciales para la funcion escogida
    public List<SillaTM> consultarSillasFuncion(int codigoFuncion, String tipoSilla){
        Pedido pedido = new Pedido();
        pedido = sillasParaLaFuncion(codigoFuncion, tipoSilla);
        pedido = disponibilidadSillas(pedido);
        guardarArchivo(pedido);

        return (List<SillaTM>)pedido.getListadoSillasTM();
    }

    public void seleccionarSillas(List<SillaTM> sillasTM){

        Pedido pedido = new Pedido();

        pedido = guardarSillas(sillasTM);
        guardarArchivo(pedido);
    }

    public FacturaCompraTM seleccionarSnacks(Optional<List<SnackTM>> snacksTM){

        Pedido pedido = new Pedido();

        pedido = guardarSnacks(snacksTM);
        guardarArchivo(pedido);

        return generarFactura();

    }

    public FacturaCompraTM generarFactura(){

        FacturaCompraTM factura = new FacturaCompraTM();

        factura = generarFacturaCompra();

        return factura;
    }

    public void pagarFactura(boolean puntosRedimidos){

        int codigoFacturaCompra = registrarFacturaCompra(puntosRedimidos);
        registrarDispoSillas(codigoFacturaCompra);
        registrarCompraSnack(codigoFacturaCompra);
        modificarVentaSnack();
        modificarPuntosCliente();

        Pedido pedido = (Pedido)leerArchivo();

    }


    public abstract Pedido guardarInfoBasica(Pedido pedido, int codigoMultiplex, int codigoPelicula, Date fechaFuncion);
    public abstract Pedido leerArchivo();
    public abstract void guardarArchivo(Pedido pedido);
    public abstract Pedido funcionesPorPeliculaAndFecha(Pedido pedido);
    public abstract Pedido funcionesEnMultiplex(Pedido pedido);
    public abstract Pedido sillasParaLaFuncion(int codigoFuncion, String tipoSilla);
    public abstract Pedido disponibilidadSillas(Pedido pedido);
    public abstract Pedido quintoPaso(Pedido pedido);
    public abstract Pedido sextoPaso(Pedido pedido);
    public abstract Pedido guardarSillas(List<SillaTM> sillasTM);
    public abstract Pedido guardarSnacks(Optional<List<SnackTM>> snacksTM);
    public abstract FacturaCompraTM generarFacturaCompra();
    public abstract int registrarFacturaCompra(boolean puntosRedimidos);
    public abstract void registrarDispoSillas(int codigoFacturaCompra);
    public abstract void registrarCompraSnack(int codigoFactura);
    public abstract void modificarVentaSnack();
    public abstract void modificarPuntosCliente();
}
