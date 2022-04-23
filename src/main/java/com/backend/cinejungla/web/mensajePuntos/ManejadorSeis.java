package com.backend.cinejungla.web.mensajePuntos;

public class ManejadorSeis extends ManejadorPuntos{

    @Override
    public String manejarPeticion(int puntosCliente){

        return "Obtuviste " + puntosCliente +  " puntos, ahora puedes redimirlos por una boleta general gratis en tu siguiente compra para la película que desees!";
    }
}
