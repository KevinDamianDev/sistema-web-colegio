package com.eventos.deportivos.backend.models.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceEntity<T, ID> {


    List<T> listar();
    T porId(Long id);
    T guardar(T entidad);
    void eliminar(ID id);
    boolean nombreExiste(String ent);
}
