package com.eventos.deportivos.backend.models.service;

import com.eventos.deportivos.backend.models.entity.Persona;

public interface IPersonaServiceSpring {
    public Persona findByUserName(String usuarioPersona);
}
