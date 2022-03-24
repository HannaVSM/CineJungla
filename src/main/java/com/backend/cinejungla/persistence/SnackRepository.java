package com.backend.cinejungla.persistence;

import com.backend.cinejungla.persistence.crud.SnackCrudRepository;
import com.backend.cinejungla.persistence.entity.Snack;

import java.util.List;
import java.util.Optional;

public class SnackRepository {

    private SnackCrudRepository snackCrudRepository;

    public List <Snack> getAll(){return (List<Snack>) snackCrudRepository.getAll();}

    public Optional<Snack> getSnackByCodigo(int codigoSnack){return (Optional<Snack>) snackCrudRepository.getSnackByCodigo(codigoSnack);}
}

