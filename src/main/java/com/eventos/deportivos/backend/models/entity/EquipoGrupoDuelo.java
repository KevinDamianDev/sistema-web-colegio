package com.eventos.deportivos.backend.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "equipo_grupo_duelo")
public class EquipoGrupoDuelo implements Serializable {
    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipoGrupoDuelo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEquipoGrupo")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private EquipoGrupo equipoGrupo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDuelo")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Duelo duelo;

    public Long getIdEquipoGrupoDuelo() {
        return idEquipoGrupoDuelo;
    }

    public void setIdEquipoGrupoDuelo(Long idEquipoGrupoDuelo) {
        this.idEquipoGrupoDuelo = idEquipoGrupoDuelo;
    }

    public EquipoGrupo getEquipoGrupo() {
        return equipoGrupo;
    }

    public void setEquipoGrupo(EquipoGrupo equipoGrupo) {
        this.equipoGrupo = equipoGrupo;
    }

    public Duelo getDuelo() {
        return duelo;
    }

    public void setDuelo(Duelo duelo) {
        this.duelo = duelo;
    }
}
