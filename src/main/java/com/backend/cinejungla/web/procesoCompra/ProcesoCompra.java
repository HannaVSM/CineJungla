package com.backend.cinejungla.web.procesoCompra;

import com.backend.cinejungla.persistence.entity.Cliente;
import com.backend.cinejungla.persistence.entity.Funcion;
import com.backend.cinejungla.persistence.entity.Silla;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.*;

public abstract class ProcesoCompra implements Serializable {
/*
    public List<Funcion> pruebaFunciones (int codigoMultiplex, int codigoPelicula, Date fechaFuncion){
        Pedido pedido = new Pedido();
        guardarInfoBasica(pedido, codigoMultiplex, codigoPelicula, fechaFuncion);
        List<Funcion> funciones = funcionesPorPeliculaAndFecha();

        return funciones;
    }
*/
    //Se hacen los dos filtros para encontrar las funciones para una pelicula, en un multiplex, en un cierto dia
    public List<Funcion> consultarFunciones(int codigoMultiplex, int codigoPelicula, Date fechaFuncion){

        crearTablasTemporales();

        guardarInfoBasica(codigoMultiplex, codigoPelicula);
        List<Funcion> funciones = funcionesPorPeliculaAndFecha(fechaFuncion);
        funciones = funcionesEnMultiplex(funciones);

        return funciones;
    }

    //Se hacen los filtros  para encontrar las sillas generales o preferenciales para la funcion escogida
    public List<SillaTM> consultarSillasFuncion(int codigoFuncion, String tipoSilla){

        List<Silla> sillas = sillasParaLaFuncion(codigoFuncion, tipoSilla);
        return disponibilidadSillas(sillas);

        //return (List<SillaTM>)leerPedido().getListadoSillasTM();
    }

    public void seleccionarSillas(List<SillaTM> sillasTM){

        guardarSillas(sillasTM);
    }

    public void seleccionarSnacks(Optional<List<SnackTM>> snacksTM){

        guardarSnacks(snacksTM);
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


    public static void crearTablasTemporales(){
        Connection miConexion = null;

        Statement miStatement = null;
        ResultSet miResultset = null;

        CallableStatement miSentencia = null;

        try{

            //1. CREAR CONEXION

            if(ConexionBD.estadoConexion()){
                ConexionBD.cerrarConexion();
                miConexion = ConexionBD.getConexion();
            }
            else{
                miConexion = ConexionBD.getConexion();
            }


            //2. CREAR OBJETO DE TIPO CallableStatement

            miSentencia = miConexion.prepareCall("{call crear_tablas_temporales}");
            miResultset = miSentencia.executeQuery();
        }
        catch(Exception e){
            System.out.println("NO CONECTA CON LA BASE DE DATOS");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        finally{
            //miSentencia.close();
            //miConexion.close();
        }
    }

    public static void insertarPedido(int codigoMultiplex, int codigoPelicula){
        Connection miConexion = null;

        Statement miStatement = null;
        ResultSet miResultset = null;
        PreparedStatement miSentencia = null;


        try{

            //1. CREAR CONEXION

            miConexion = ConexionBD.getConexion();

            //2. CREAR OBJETO DE TIPO prepareStatement

            String sentenciaSql = "INSERT INTO pedido (codigo_multiplex, codigo_pelicula) VALUES (?, ?)";
            miSentencia = miConexion.prepareStatement(sentenciaSql);

            miSentencia.setInt(1, codigoMultiplex);
            miSentencia.setInt(2, codigoPelicula);

            miSentencia.executeUpdate();
        }
        catch(Exception e){
            System.out.println("NO CONECTA CON LA BASE DE DATOS");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        finally{
            //miSentencia.close();
            //miConexion.close();
        }
    }

    public static void actualizarPedido(int codigoFuncion, String tipoSilla){
        Connection miConexion = null;

        Statement miStatement = null;
        ResultSet miResultset = null;
        PreparedStatement miSentencia = null;


        try{

            //1. CREAR CONEXION

            //miConexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Cine_jungla_BD", "postgres", "admin");
            miConexion = ConexionBD.getConexion();

            //2. CREAR OBJETO DE TIPO prepareStatement

            String sentenciaSql = "UPDATE pedido SET codigo_funcion = ?, tipo_silla = ? WHERE codigo_pedido = 1";
            miSentencia = miConexion.prepareStatement(sentenciaSql);

            miSentencia.setInt(1, codigoFuncion);
            miSentencia.setString(2, tipoSilla);

            miSentencia.executeUpdate();
        }
        catch(Exception e){
            System.out.println("NO CONECTA CON LA BASE DE DATOS");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        finally{
            //miSentencia.close();
            //miConexion.close();
        }
    }

    public static Pedido consultarPedido(){
        Connection miConexion = null;

        PreparedStatement miSentencia = null;
        ResultSet miResultset = null;

        Pedido pedido = new Pedido();

        try{

            //1. CREAR CONEXION

            miConexion = ConexionBD.getConexion();

            //2. CREAR OBJETO DE TIPO prepareStatement

            String sentenciaSql = "SELECT * FROM pedido";
            miSentencia = miConexion.prepareStatement(sentenciaSql);

            //3. EJECUTAR SQL


            miResultset = miSentencia.executeQuery();

            while(miResultset.next()){

                pedido.setCodigoPedido(miResultset.getInt(1));
                pedido.setCodigoMultiplex(miResultset.getInt(2));
                pedido.setCodigoPelicula(miResultset.getInt(3));
                pedido.setCodigoFuncion(miResultset.getInt(4));
                pedido.setTipoSilla(miResultset.getString(5));
            }
        }

        catch(Exception e){
            System.out.println("NO CONECTA CON LA BASE DE DATOS");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        finally{
            //miSentencia.close();
            //miConexion.close();
        }

        return pedido;
    }

    public static void insertarDetDispoSillaTemp(int codigoSilla, int codigoFuncion, int codigoPedido, boolean dispoSilla){
        Connection miConexion = null;

        Statement miStatement = null;
        ResultSet miResultset = null;
        PreparedStatement miSentencia = null;

        String sentenciaSql = "";


        try{

            //1. CREAR CONEXION

            miConexion = ConexionBD.getConexion();

            //2. CREAR OBJETO DE TIPO prepareStatement

            sentenciaSql = "INSERT INTO detalle_dispo_silla_temp(codigo_silla, codigo_funcion, codigo_pedido, disponibilidad_silla) VALUES (?, ?, ?, ?)";
            miSentencia = miConexion.prepareStatement(sentenciaSql);

            miSentencia.setInt(1, codigoSilla);
            miSentencia.setInt(2, codigoFuncion);
            miSentencia.setInt(3, codigoPedido);
            miSentencia.setBoolean(4, dispoSilla);

            miSentencia.executeUpdate();
        }
        catch(Exception e){
            System.out.println("NO CONECTA CON LA BASE DE DATOS");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        finally{
            //miSentencia.close();
            //miConexion.close();
        }
    }

    public static void eliminarDetDispoSillaTemp(int codigoPedido){
        Connection miConexion = null;

        PreparedStatement miSentencia = null;

        String sentenciaSql = "";


        try{

            //1. CREAR CONEXION

            miConexion = ConexionBD.getConexion();

            //2. CREAR OBJETO DE TIPO prepareStatement

            sentenciaSql = "DELETE FROM detalle_dispo_silla_temp WHERE codigo_pedido = ?";
            miSentencia = miConexion.prepareStatement(sentenciaSql);
            miSentencia.setInt(1, codigoPedido);
            miSentencia.executeUpdate();

        }
        catch(Exception e){
            System.out.println("NO CONECTA CON LA BASE DE DATOS");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        finally{
            //miSentencia.close();
            //miConexion.close();
        }
    }

    public static List<DetalleDispoSillaTemp> getDetDispoSillaTemp(){
        Connection miConexion = null;

        PreparedStatement miSentencia = null;
        ResultSet miResultset = null;

        List<DetalleDispoSillaTemp> sillasTemp = new ArrayList<DetalleDispoSillaTemp>();

        Pedido pedido = consultarPedido();

        try{

            //1. CREAR CONEXION

            miConexion = ConexionBD.getConexion();

            //2. CREAR OBJETO DE TIPO prepareStatement

            String sentenciaSql = "SELECT * FROM detalle_dispo_silla_temp WHERE codigo_pedido = ?";
            miSentencia = miConexion.prepareStatement(sentenciaSql);
            miSentencia.setInt(1, pedido.getCodigoPedido());

            //3. EJECUTAR SQL


            miResultset = miSentencia.executeQuery();

            while(miResultset.next()){

                DetalleDispoSillaTemp detalleDispoSillaTemp = new DetalleDispoSillaTemp();

                detalleDispoSillaTemp.setCodigoSilla(miResultset.getInt(1));
                detalleDispoSillaTemp.setCodigoFuncion(miResultset.getInt(2));
                detalleDispoSillaTemp.setCodigoPedido(miResultset.getInt(3));
                detalleDispoSillaTemp.setDisponibilidadSilla(miResultset.getBoolean(4));

                sillasTemp.add(detalleDispoSillaTemp);

            }
        }
        catch(Exception e){
            System.out.println("NO CONECTA CON LA BASE DE DATOS");
            System.out.println(e.getMessage());
            System.out.println(e.toString());
        }
        finally{
            //miSentencia.close();
            //miConexion.close();
        }
        return sillasTemp;
    }


    public static void insertarCompraSnackTemp(int codigoSnack, int codigoPedido, int cantidadComprada){
        Connection miConexion = null;

        Statement miStatement = null;
        ResultSet miResultset = null;
        PreparedStatement miSentencia = null;

        String sentenciaSql = "";


        try{

            //1. CREAR CONEXION

            //miConexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Cine_jungla_BD", "postgres", "admin");
            miConexion = ConexionBD.getConexion();

            //2. CREAR OBJETO DE TIPO prepareStatement

            sentenciaSql = "INSERT INTO compra_snack_temp(codigo_snack, codigo_pedido, cantidad_snack_comprado) VALUES (?, ?, ?)";
            miSentencia = miConexion.prepareStatement(sentenciaSql);

            miSentencia.setInt(1, codigoSnack);
            miSentencia.setInt(2, codigoPedido);
            miSentencia.setInt(3, cantidadComprada);

            miSentencia.executeUpdate();
        }
        catch(Exception e){
            System.out.println("NO CONECTA CON LA BASE DE DATOS");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        finally{
            //miSentencia.close();
            //miConexion.close();
        }
    }

    public static void eliminarCompraSnackTemp(int codigoPedido){
        Connection miConexion = null;

        PreparedStatement miSentencia = null;

        String sentenciaSql = "";


        try{

            //1. CREAR CONEXION

            //miConexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Cine_jungla_BD", "postgres", "admin");
            miConexion = ConexionBD.getConexion();

            //2. CREAR OBJETO DE TIPO prepareStatement

            sentenciaSql = "DELETE FROM compra_snack_temp WHERE codigo_pedido = ?";
            miSentencia = miConexion.prepareStatement(sentenciaSql);
            miSentencia.setInt(1, codigoPedido);
            miSentencia.executeUpdate();

        }
        catch(Exception e){
            System.out.println("NO CONECTA CON LA BASE DE DATOS");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        finally{
            //miSentencia.close();
            //miConexion.close();
        }
    }

    public static List<CompraSnackTemp> getCompraSnackTemp(){
        Connection miConexion = null;

        PreparedStatement miSentencia = null;
        ResultSet miResultset = null;

        List<CompraSnackTemp> snacksTemp = new ArrayList<CompraSnackTemp>();

        Pedido pedido = consultarPedido();

        try{

            //1. CREAR CONEXION

            miConexion = ConexionBD.getConexion();

            //2. CREAR OBJETO DE TIPO prepareStatement

            String sentenciaSql = "SELECT * FROM compra_snack_temp WHERE codigo_pedido = ?";
            miSentencia = miConexion.prepareStatement(sentenciaSql);
            miSentencia.setInt(1, pedido.getCodigoPedido());

            //3. EJECUTAR SQL


            miResultset = miSentencia.executeQuery();

            while(miResultset.next()){

                CompraSnackTemp compraSnackTemp = new CompraSnackTemp();

                compraSnackTemp.setCodigoSnack(miResultset.getInt(1));
                compraSnackTemp.setCodigoPedido(miResultset.getInt(2));
                compraSnackTemp.setCantidadSnackComprado(miResultset.getInt(3));

                snacksTemp.add(compraSnackTemp);

            }
        }
        catch(Exception e){
            System.out.println("NO CONECTA CON LA BASE DE DATOS");
            System.out.println(e.getMessage());
            System.out.println(e.toString());
        }
        finally{
            //miSentencia.close();
            //miConexion.close();
        }
        return snacksTemp;
    }

    public abstract Cliente leerArchivoCliente();
    public abstract void guardarArchivoCliente(Cliente cliente);
    public abstract void guardarInfoBasica(int codigoMultiplex, int codigoPelicula);
    public abstract List<Funcion> funcionesPorPeliculaAndFecha(Date fechaFuncion);
    public abstract List<Funcion> funcionesEnMultiplex(List<Funcion> funciones);
    public abstract List<Silla> sillasParaLaFuncion(int codigoFuncion, String tipoSilla);
    public abstract List<SillaTM> disponibilidadSillas(List<Silla> sillas);
    public abstract void guardarSillas(List<SillaTM> sillasTM);
    public abstract void guardarSnacks(Optional<List<SnackTM>> snacksTM);
    public abstract boolean posibilidadRedimirPuntos();
    public abstract FacturaCompraTM generarFacturaCompra();
    public abstract int registrarFacturaCompra(boolean puntosRedimidos);
    public abstract void registrarDispoSillas(int codigoFacturaCompra);
    public abstract void registrarCompraSnack(int codigoFactura);
    public abstract void modificarVentaSnack();
    public abstract void modificarPuntosCliente();
}