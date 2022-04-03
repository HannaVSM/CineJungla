package com.backend.cinejungla.web.mensajePuntos;

import com.backend.cinejungla.persistence.entity.Cliente;

public class ManejadorCinco extends ManejadorPuntos{

    @Override
    public String manejarPeticion(int puntosCliente){

        if(puntosCliente>=75 && puntosCliente<100){
            return "Tienes " + puntosCliente +  " puntos, ya estás cerca de esa boleta gratis!";
        }
        else{
            return sucesor.manejarPeticion(puntosCliente);
        }
    }
}
