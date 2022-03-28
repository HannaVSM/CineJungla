package com.backend.cinejungla.domain.service;

import com.backend.cinejungla.persistence.crud.VentaSnackCrudRepository;
import com.backend.cinejungla.persistence.entity.Snack;
import com.backend.cinejungla.persistence.entity.VentaSnack;
import com.backend.cinejungla.web.procesoCompra.SnackTM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VentaSnackService {

    @Autowired
    private VentaSnackCrudRepository ventaSnackCrudRepository;

    @Autowired
    private SnackService snackService;

    public Optional <List<VentaSnack>> disponibilidadSnack(int stock){
        return ventaSnackCrudRepository.findByStockActualGreaterThan(stock);
    }

    public List<VentaSnack> getAllByCodigoMultiplex(int codigoMultiplex){ return (List<VentaSnack>) ventaSnackCrudRepository.getAllByCodigoMultiplex(codigoMultiplex); }

    public Optional<VentaSnack> getByCodigoMultiplexAndSnack(int codigoMultiplex, int codigoSnack){
        return ventaSnackCrudRepository.getByCodigoMultiplexAndSnack(codigoMultiplex, codigoSnack);
    }

    public void actualizarVentaSnack(int stockNuevo, int codigoMultiplex, int codigoSnack){
        ventaSnackCrudRepository.actualizarVentaSnack(stockNuevo, codigoMultiplex, codigoSnack);
    }

    public List<SnackTM> getVentaSnacksInMultiplex(int codigoMultiplex){


        List<SnackTM> listaSnacksTM = new ArrayList<SnackTM>();

        //List<VentaSnack> ventaSnacks = getAllByCodigoMultiplex(pedido.getMultiplex().getCodigoMultiplex());

        List<VentaSnack> ventaSnacks = getAllByCodigoMultiplex(codigoMultiplex);

        for(int i=0; i<ventaSnacks.size(); i++){
            int codigoSnack = ventaSnacks.get(i).getSnack().getCodigoSnack();
            //System.out.println(ventaSnacks.get(i).getId().getCodigoSnack());
            Optional<Snack> snack = snackService.getSnackByCodigo(codigoSnack);
            SnackTM snackTM = new SnackTM();
            snackTM.setCodigoSnack(snack.get().getCodigoSnack());
            //System.out.println(snack.get().getCodigoSnack());
            snackTM.setNombreSnack(snack.get().getNombreSnack());
            snackTM.setPrecioUnitario(snack.get().getPrecioUnitario());
            snackTM.setCantidadStock(ventaSnacks.get(i).getStockActual());

            listaSnacksTM.add(snackTM);
        }
        return listaSnacksTM;
    }
}
