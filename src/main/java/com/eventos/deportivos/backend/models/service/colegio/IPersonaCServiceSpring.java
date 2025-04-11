package com.eventos.deportivos.backend.models.service.colegio;

import com.eventos.deportivos.backend.models.entity.colegio.PersonaC;

public interface IPersonaCServiceSpring {
    public PersonaC findByUserName(String usuarioPersona);
}
