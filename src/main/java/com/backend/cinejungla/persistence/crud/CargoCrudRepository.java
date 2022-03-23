package com.backend.cinejungla.persistence.crud;

import com.backend.cinejungla.persistence.entity.Cargo;
import org.springframework.data.repository.CrudRepository;

public interface CargoCrudRepository extends CrudRepository <Cargo, Integer> {
}
