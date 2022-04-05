package com.backend.cinejungla.web.procesoCompra;

import com.backend.cinejungla.persistence.entity.Cliente;
import com.backend.cinejungla.persistence.entity.Funcion;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public abstract class ProcesoCompra implements Serializable {

    public List<Funcion> pruebaFunciones (int codigoMultiplex, int codigoPelicula, Date fechaFuncion){
        Pedido pedido = new Pedido();
        guardarInfoBasica(pedido, codigoMultiplex, codigoPelicula, fechaFuncion);
        List<Funcion> funciones = funcionesPorPeliculaAndFecha();

        return funciones;
    }

    //Se hacen los dos filtros para encontrar las funciones para una pelicula, en un multiplex, en un cierto dia
    public List<Funcion> consultarFunciones(int codigoMultiplex, int codigoPelicula, Date fechaFuncion){

        Pedido pedido = new Pedido();

        guardarInfoBasica(pedido, codigoMultiplex, codigoPelicula, fechaFuncion);
        List<Funcion> funciones = funcionesPorPeliculaAndFecha();
        funciones = funcionesEnMultiplex(funciones);

        return funciones;
    }

    //Se hacen los filtros  para encontrar las sillas generales o preferenciales para la funcion escogida
    public List<SillaTM> consultarSillasFuncion(int codigoFuncion, String tipoSilla){
        Pedido pedido = new Pedido();
        pedido = sillasParaLaFuncion(codigoFuncion, tipoSilla);
        pedido = disponibilidadSillas(pedido);
        guardarPedido(pedido);

        return (List<SillaTM>)pedido.getListadoSillasTM();
    }

    public void seleccionarSillas(List<SillaTM> sillasTM){

        Pedido pedido = new Pedido();

        pedido = guardarSillas(sillasTM);
        guardarPedido(pedido);
    }

    public void seleccionarSnacks(Optional<List<SnackTM>> snacksTM){

        Pedido pedido = new Pedido();

        pedido = guardarSnacks(snacksTM);
        guardarPedido(pedido);
    }


    public FacturaCompraTM generarFactura(){

        FacturaCompraTM factura = new FacturaCompraTM();

        factura = generarFacturaCompra();

        return factura;
    }

    public void pagarFactura(boolean redimirPuntos){

        int codigoFacturaCompra = registrarFacturaCompra(redimirPuntos);
        registrarDispoSillas(codigoFacturaCompra);
        registrarCompraSnack(codigoFacturaCompra);
        modificarVentaSnack();
        modificarPuntosCliente();
    }

    public abstract Cliente leerArchivoCliente();
    public abstract void guardarArchivoCliente(Cliente cliente);
    public abstract void guardarInfoBasica(Pedido pedido, int codigoMultiplex, int codigoPelicula, Date fechaFuncion);
    public abstract Pedido leerPedido();
    public abstract void guardarPedido(Pedido pedido);
    public abstract List<Funcion> funcionesPorPeliculaAndFecha();
    public abstract List<Funcion> funcionesEnMultiplex(List<Funcion> funciones);
    public abstract Pedido sillasParaLaFuncion(int codigoFuncion, String tipoSilla);
    public abstract Pedido disponibilidadSillas(Pedido pedido);
    public abstract Pedido guardarSillas(List<SillaTM> sillasTM);
    public abstract Pedido guardarSnacks(Optional<List<SnackTM>> snacksTM);
    public abstract boolean posibilidadRedimirPuntos();
    public abstract FacturaCompraTM generarFacturaCompra();
    public abstract int registrarFacturaCompra(boolean puntosRedimidos);
    public abstract void registrarDispoSillas(int codigoFacturaCompra);
    public abstract void registrarCompraSnack(int codigoFactura);
    public abstract void modificarVentaSnack();
    public abstract void modificarPuntosCliente();
}
