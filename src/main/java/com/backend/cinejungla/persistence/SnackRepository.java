package com.backend.cinejungla.persistence;

import com.backend.cinejungla.persistence.crud.SnackCrudRepository;
import com.backend.cinejungla.persistence.entity.Snack;

import java.util.List;

public class SnackRepository {

    private SnackCrudRepository snackCrudRepository;

    public List <Snack> getAllSnacks(int codigoSnack){
        return (List<Snack>) snackCrudRepository.findAll();
    }
}
