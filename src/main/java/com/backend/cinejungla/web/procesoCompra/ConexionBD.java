package com.backend.cinejungla.web.procesoCompra;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {

    private static Connection conexion;

    public static Connection getConexion(){
        try{
            if(conexion == null || conexion.isClosed()){
                conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Cine_jungla_BD", "postgres", "admin");
            }
        }
        catch(Exception e){
            System.out.println("NO CONECTA CON LA BASE DE DATOS");
            System.out.println(e.getMessage());
        }
        return conexion;
    }

    public static boolean estadoConexion(){

        boolean disponibilidad = true;

        try{
            if(conexion == null || conexion.isClosed()){
                disponibilidad = false;
            }
        }
        catch(Exception e){}

        return disponibilidad;
    }

    public static void cerrarConexion(){
        try{
            conexion.close();
        }
        catch(Exception e){}
    }
}
