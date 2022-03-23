package com.backend.cinejungla.persistence.crud;

import com.backend.cinejungla.persistence.entity.Pelicula;
import org.springframework.data.repository.CrudRepository;

public interface PeliculaCrudRepository extends CrudRepository <Pelicula, Integer> {
}
