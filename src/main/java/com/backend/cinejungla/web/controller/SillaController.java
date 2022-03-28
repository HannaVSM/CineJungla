package com.backend.cinejungla.web.controller;

import com.backend.cinejungla.domain.service.SillaService;
import com.backend.cinejungla.persistence.crud.SillaCrudRepository;
import com.backend.cinejungla.persistence.entity.Silla;
import com.backend.cinejungla.web.procesoCompra.ProcesoCompra;
import com.backend.cinejungla.web.procesoCompra.ProcesoConcreto;
import com.backend.cinejungla.web.procesoCompra.SillaTM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = ("http://localhost:4200"))
@RestController
@RequestMapping("/silla")
public class SillaController {

    @Autowired
    private SillaService sillaService;

    @GetMapping("/precio/{precio}")
    public List<Silla> getSillaByPrice (@PathVariable("precio") int precioSilla){
        return sillaService.getSillaByPrice(precioSilla);
    }

    @GetMapping("/{codigo}")
    public List<Silla> getSillasByCodigoSala (@PathVariable("codigo")int codigoSala){ return (List<Silla>) sillaService.getSillaByPrice(codigoSala); }


    @GetMapping("/{codigo}/{tipo}")
    public List<Silla> getSillasByCodigoSalaAndTipo (@PathVariable("codigo") int codigoSala, @PathVariable("tipo") String tipoSilla){
        return (List<Silla>) sillaService.getSillasByCodigoSalaAndTipo(codigoSala, tipoSilla);
    }

    @GetMapping("/sillasFuncion/{codigoFuncion}/{tipoSilla}")
    public List<SillaTM> getSillasParaUnaFuncion (@PathVariable("codigoFuncion") int codigoFuncion, @PathVariable("tipoSilla") String tipoSilla){

        ProcesoCompra proceso = new ProcesoConcreto();

        return (List<SillaTM>)proceso.consultarSillasFuncion(codigoFuncion, tipoSilla);


        //return (List<Silla>) sillaService.getSillasByCodigoSalaAndTipo(codigoFuncion, tipoSilla);
    }
}
