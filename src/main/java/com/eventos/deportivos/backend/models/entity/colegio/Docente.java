package com.eventos.deportivos.backend.models.entity.colegio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
@Entity
@Table(name = "docente")
public class Docente {
    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDocente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersona")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private PersonaC persona;


    public Long getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Long idDocente) {
        this.idDocente = idDocente;
    }

    public PersonaC getPersona() {
        return persona;
    }

    public void setPersona(PersonaC persona) {
        this.persona = persona;
    }
}
