package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.Categoria;
import com.eventos.deportivos.backend.models.entity.Evento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICategoriaDao extends CrudRepository<Categoria, Long> {
    boolean existsByNombreCategoria(String nombre);

    @Query("SELECT e FROM Evento e WHERE e.categoria.idCategoria = :categoriaId")
    List<Evento> findByCategoria(@Param("categoriaId") Long categoriaId);


}
