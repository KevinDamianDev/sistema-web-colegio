package com.eventos.deportivos.backend.models.service;


import java.util.List;

public interface ServiceEntity<T, ID> {

    List<T> listar();
    T porId(Long id);
    T guardar(T entidad);
    void eliminar(ID id);
    boolean nombreExiste(String ent);
}
