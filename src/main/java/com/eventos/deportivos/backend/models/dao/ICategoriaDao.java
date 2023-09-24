package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface ICategoriaDao extends CrudRepository<Categoria, Long> {
    boolean existsByNombreCategoria(String nombre);
}
