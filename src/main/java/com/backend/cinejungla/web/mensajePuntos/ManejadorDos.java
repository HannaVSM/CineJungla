package com.backend.cinejungla.web.mensajePuntos;

public class ManejadorDos extends ManejadorPuntos{

    @Override
    public String manejarPeticion(int puntosCliente){

        if(puntosCliente<25){
            return "Tienes " + puntosCliente +  " puntos, recuerda que si consigues 100 puntos, podrás canjearlos por una boleta de tipo general para la película que desees!";
        }
        else{
            return sucesor.manejarPeticion(puntosCliente);
        }
    }
}
