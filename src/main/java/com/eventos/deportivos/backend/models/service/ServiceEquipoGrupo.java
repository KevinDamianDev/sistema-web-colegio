package com.eventos.deportivos.backend.models.service;

import java.util.List;

public interface ServiceEquipoGrupo <T, ID>{
    List<T> listarEquipoByGrupo(Long id);
}
