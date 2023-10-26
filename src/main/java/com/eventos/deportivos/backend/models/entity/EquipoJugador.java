package com.eventos.deportivos.backend.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "equipo_jugador")
public class EquipoJugador implements Serializable {
    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipoJugador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEquipo")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Equipo equipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idJugador")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Jugador jugador;

    public Long getIdEquipoJugador() {
        return idEquipoJugador;
    }

    public void setIdEquipoJugador(Long idEquipoJugador) {
        this.idEquipoJugador = idEquipoJugador;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
}
