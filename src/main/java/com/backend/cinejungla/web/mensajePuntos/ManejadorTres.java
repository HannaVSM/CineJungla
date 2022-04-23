package com.backend.cinejungla.web.mensajePuntos;

public class ManejadorTres extends ManejadorPuntos{

    @Override
    public String manejarPeticion(int puntosCliente){

        if(puntosCliente>=25 && puntosCliente<50){
            return "Tienes " + puntosCliente +  " puntos, recuerda que por cada delicioso snack que compres ganas 5 puntos";
        }
        else{
            return sucesor.manejarPeticion(puntosCliente);
        }
    }
}
