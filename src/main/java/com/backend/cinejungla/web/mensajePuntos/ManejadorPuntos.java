package com.backend.cinejungla.web.mensajePuntos;

import com.backend.cinejungla.persistence.entity.Cliente;

public abstract class ManejadorPuntos {

    ManejadorPuntos sucesor;

    public abstract String manejarPeticion(int puntosCliente);

    public ManejadorPuntos getSucesor(){
        return this.sucesor;
    }

    public void setSucesor(ManejadorPuntos sucesor){
        this.sucesor = sucesor;
    }
}
