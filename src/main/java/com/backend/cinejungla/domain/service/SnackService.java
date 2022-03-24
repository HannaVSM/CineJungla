package com.backend.cinejungla.domain.service;

import com.backend.cinejungla.persistence.crud.SnackCrudRepository;
import com.backend.cinejungla.persistence.entity.Snack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SnackService {

    @Autowired
    private SnackCrudRepository snackCrudRepository;

    public List <Snack> getAll(){return (List<Snack>) snackCrudRepository.getAll();}

    public Optional<Snack> getSnackByCodigo(int codigoSnack){return (Optional<Snack>) snackCrudRepository.getSnackByCodigo(codigoSnack);}
}

