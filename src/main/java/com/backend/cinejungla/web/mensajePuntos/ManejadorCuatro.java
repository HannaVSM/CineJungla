package com.backend.cinejungla.web.mensajePuntos;

import com.backend.cinejungla.persistence.entity.Cliente;

public class ManejadorCuatro extends ManejadorPuntos{

    @Override
    public String manejarPeticion(int puntosCliente){

        if(puntosCliente>=50 && puntosCliente<75){
            return "Tienes " + puntosCliente +  " puntos, vas por buen camino para conseguir esa boleta general gratis!";
        }
        else{
            return sucesor.manejarPeticion(puntosCliente);
        }
    }
}
