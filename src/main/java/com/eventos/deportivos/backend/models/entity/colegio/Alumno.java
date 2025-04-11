package com.eventos.deportivos.backend.models.entity.colegio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "alumno")
public class Alumno {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlumno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersona")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private PersonaC persona;

    public Long getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Long idAlumno) {
        this.idAlumno = idAlumno;
    }

    public PersonaC getPersona() {
        return persona;
    }

    public void setPersona(PersonaC persona) {
        this.persona = persona;
    }
}
