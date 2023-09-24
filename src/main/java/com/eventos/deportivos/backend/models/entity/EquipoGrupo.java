package com.eventos.deportivos.backend.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "equipos_grupos")
public class EquipoGrupo implements Serializable {

    private static final long serialVersionUID =1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipoGrupo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEquipo")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Equipo equipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idGrupo")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Grupo grupo;

    public Long getIdEquipoGrupo() {
        return idEquipoGrupo;
    }

    public void setIdEquipoGrupo(Long idEquipoGrupo) {
        this.idEquipoGrupo = idEquipoGrupo;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}
