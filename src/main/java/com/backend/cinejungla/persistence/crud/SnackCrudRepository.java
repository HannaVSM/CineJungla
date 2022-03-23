package com.backend.cinejungla.persistence.crud;

import com.backend.cinejungla.persistence.entity.Snack;
import org.springframework.data.repository.CrudRepository;

public interface SnackCrudRepository extends CrudRepository <Snack, Integer> {
}
