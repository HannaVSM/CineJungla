package com.backend.cinejungla.persistence;

import com.backend.cinejungla.persistence.crud.CompraSnackCrudRepository;
import com.backend.cinejungla.persistence.entity.CompraSnack;
import com.backend.cinejungla.persistence.entity.CompraSnackPK;

import java.util.Optional;

public class CompraSnackRepository {

    private CompraSnackCrudRepository compraSnackCrudRepository;

    public CompraSnack save(CompraSnack compraSnack){
        return compraSnackCrudRepository.save(compraSnack);
    }
}
