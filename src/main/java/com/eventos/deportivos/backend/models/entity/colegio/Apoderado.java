package com.eventos.deportivos.backend.models.entity.colegio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "apoderado")
public class Apoderado {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idApoderado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersona")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private PersonaC persona;

    public Long getIdApoderado() {
        return idApoderado;
    }

    public void setIdApoderado(Long idApoderado) {
        this.idApoderado = idApoderado;
    }

    public PersonaC getPersona() {
        return persona;
    }

    public void setPersona(PersonaC persona) {
        this.persona = persona;
    }
}
