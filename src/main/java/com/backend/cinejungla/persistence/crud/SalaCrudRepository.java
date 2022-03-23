package com.backend.cinejungla.persistence.crud;

import com.backend.cinejungla.persistence.entity.Sala;
import org.springframework.data.repository.CrudRepository;

public interface SalaCrudRepository extends CrudRepository <Sala, Integer> {
}
