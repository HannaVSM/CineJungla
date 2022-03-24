package com.backend.cinejungla.web.controller;

import com.backend.cinejungla.domain.service.SillaService;
import com.backend.cinejungla.persistence.crud.SillaCrudRepository;
import com.backend.cinejungla.persistence.entity.Silla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/resolverxd")
    public List<Silla> getSillasByCodigoSalaAndTipo (int codigoSala, String tipoSilla){
        return (List<Silla>) sillaService.getSillasByCodigoSalaAndTipo(codigoSala, tipoSilla);
    }
}
