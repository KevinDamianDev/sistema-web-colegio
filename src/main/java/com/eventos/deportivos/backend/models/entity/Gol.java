package com.eventos.deportivos.backend.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "goles")
public class Gol implements Serializable {

    private static final long serialVersionUID =1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGol;

    private Long minutoGol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEquipoJugador")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private EquipoJugador equipoJugador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDuelo")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Duelo duelo;

    public Long getIdGol() {
        return idGol;
    }

    public void setIdGol(Long idGol) {
        this.idGol = idGol;
    }

    public Long getMinutoGol() {
        return minutoGol;
    }

    public void setMinutoGol(Long minutoGol) {
        this.minutoGol = minutoGol;
    }

    public EquipoJugador getEquipoJugador() {
        return equipoJugador;
    }

    public void setEquipoJugador(EquipoJugador equipoJugador) {
        this.equipoJugador = equipoJugador;
    }

    public Duelo getDuelo() {
        return duelo;
    }

    public void setDuelo(Duelo duelo) {
        this.duelo = duelo;
    }
}
