package com.backend.cinejungla.web.mensajePuntos;

import com.backend.cinejungla.persistence.entity.Cliente;

public class ManejadorUno extends ManejadorPuntos{

    @Override
    public String manejarPeticion(int puntosCliente){

        if(puntosCliente == 0){
            return "No tienes puntos todavía. Recuerda que por la compra de cada boleta obtienes 10 puntos, y por cada snack 5 puntos. Gana puntos hasta obtener 100 puntos para canjearlos por una boleta de tipo general por la película de tu preferencia";
        }
        else{
            return sucesor.manejarPeticion(puntosCliente);
        }
    }
}
