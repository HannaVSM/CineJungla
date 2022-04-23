package com.backend.cinejungla.web.manejoPatrones;

import com.backend.cinejungla.persistence.entity.Cliente;
import com.backend.cinejungla.persistence.entity.Funcion;
import com.backend.cinejungla.web.mensajePuntos.*;
import com.backend.cinejungla.web.procesoCompra.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class FachadaPatrones {

    private static ProcesoCompra procesoCompra = new ProcesoConcreto();

    public static List<Funcion> consultarFunciones(int codigoMultiplex, int codigoPelicula, Date fechaFuncion){

        return procesoCompra.consultarFunciones(codigoMultiplex, codigoPelicula, fechaFuncion);
    }

    public static List<SillaTM> consultarSillasFuncion(int codigoFuncion, String tipoSilla){

        return procesoCompra.consultarSillasFuncion(codigoFuncion, tipoSilla);
    }


    public static void seleccionarSillas(List<SillaTM> sillasTM){

        procesoCompra.seleccionarSillas(sillasTM);
    }



    public static void seleccionarSnacks(Optional<List<SnackTM>> snacksTM){

        procesoCompra.seleccionarSnacks(snacksTM);
    }



    public static FacturaCompraTM generarFactura(){

        return procesoCompra.generarFactura();
    }

    public static boolean posibilidadRedimirPuntos(){
        return procesoCompra.posibilidadRedimirPuntos();
    }



    public static void pagarFactura(boolean redimirPuntos){
        procesoCompra.pagarFactura(redimirPuntos);
    }


    public static String mostrarMensaje(){

        Cliente cliente = procesoCompra.leerArchivoCliente();
        String mensaje = "";

        ManejadorPuntos manejadores[] = new ManejadorPuntos[6];

        manejadores[0] = new ManejadorUno();
        manejadores[1] = new ManejadorDos();
        manejadores[2] = new ManejadorTres();
        manejadores[3] = new ManejadorCuatro();
        manejadores[4] = new ManejadorCinco();
        manejadores[5] = new ManejadorSeis();

        for(int i=0; i<manejadores.length-1; i++){
            manejadores[i].setSucesor(manejadores[i+1]);
        }
        mensaje = manejadores[0].manejarPeticion(cliente.getPuntos());

        return mensaje;
    }
}
