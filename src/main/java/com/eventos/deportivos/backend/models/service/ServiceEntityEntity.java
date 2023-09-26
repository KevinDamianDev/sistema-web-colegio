package com.eventos.deportivos.backend.models.service;

import java.util.List;

public interface ServiceEntityEntity<T, ID> {
    List<T> listarByEntity(Long id);
}
