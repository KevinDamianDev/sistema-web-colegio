package com.eventos.deportivos.backend.models.service;

public interface ServiceEntityExisteDato<T, ID> {
    T existeRegistro(Long id1, Long id2);
}
