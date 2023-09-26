package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.Rol;
import org.springframework.data.repository.CrudRepository;

public interface IRolDao extends CrudRepository<Rol, Long> {
    boolean existsByNombreRoles(String nombre);
}
