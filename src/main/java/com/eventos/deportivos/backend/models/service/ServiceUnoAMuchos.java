package com.eventos.deportivos.backend.models.service;

import java.util.List;

public interface ServiceUnoAMuchos <T, ID>{
    List<T> listarEntitysByEntity(Long id);
}
