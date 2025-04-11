package com.eventos.deportivos.backend.models.service.colegio;


import java.util.List;

public interface ServiceEntityC<T, ID> {

    List<T> listar();
    T porId(Long id);
    T guardar(T entidad);
    void eliminar(ID id);
}
