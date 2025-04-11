package com.eventos.deportivos.backend.models.entity.colegio;

import com.eventos.deportivos.backend.models.entity.colegio.PersonaC;
import com.eventos.deportivos.backend.models.entity.colegio.RolC;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "persona_rol")
public class PersonaRolC implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersonaRol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRol")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private RolC rol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersona")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private PersonaC persona;

    public Long getIdPersonaRol() {
        return idPersonaRol;
    }

    public void setIdPersonaRol(Long idPersonaRol) {
        this.idPersonaRol = idPersonaRol;
    }

    public PersonaC getPersona() {
        return persona;
    }

    public void setPersona(PersonaC persona) {
        this.persona = persona;
    }
    public RolC getRol() {
        return rol;
    }

    public void setRol(RolC rol) {
        this.rol = rol;
    }
}
