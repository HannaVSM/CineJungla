package com.backend.cinejungla.persistence.crud;

import com.backend.cinejungla.persistence.entity.CompraSnack;
import com.backend.cinejungla.persistence.entity.CompraSnackPK;
import org.springframework.data.repository.CrudRepository;

public interface CompraSnackCrudRepository extends CrudRepository <CompraSnack, CompraSnackPK> {
}
