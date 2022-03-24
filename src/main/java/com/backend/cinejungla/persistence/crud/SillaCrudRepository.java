package com.backend.cinejungla.persistence.crud;

import com.backend.cinejungla.persistence.entity.Silla;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SillaCrudRepository extends CrudRepository <Silla, Integer> {

    @Query(value = "SELECT * FROM silla WHERE precio_silla = ?", nativeQuery = true)
    List<Silla> sillaByPrice(int precioSilla);

    @Query(value = "SELECT * FROM silla WHERE codigo_sala = ?", nativeQuery = true)
    List<Silla> sillasByCodigoSala(int codigoSala);

    @Query(value = "SELECT * FROM silla WHERE codigo_sala = ? AND tipo_silla = ?", nativeQuery = true)
    List<Silla> sillasByCodigoSalaAndTipo(int codigoSala, String tipoSilla);
}
