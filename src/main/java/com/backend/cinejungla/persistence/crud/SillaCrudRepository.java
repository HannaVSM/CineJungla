package com.backend.cinejungla.persistence.crud;

import com.backend.cinejungla.persistence.entity.Silla;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SillaCrudRepository extends CrudRepository <Silla, Integer> {

    @Query(value = "SELECT * FROM silla WHERE precio_silla = ?", nativeQuery = true)
    List<Silla> sillaByPrice(int precioSilla);
}
