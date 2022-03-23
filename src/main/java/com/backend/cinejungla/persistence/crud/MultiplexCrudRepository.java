package com.backend.cinejungla.persistence.crud;

import com.backend.cinejungla.persistence.entity.Multiplex;
import org.springframework.data.repository.CrudRepository;

public interface MultiplexCrudRepository extends CrudRepository <Multiplex, Integer> {
}
