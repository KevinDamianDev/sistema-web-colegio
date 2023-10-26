package com.eventos.deportivos.backend.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "evento_equipo")
public class EventoEquipo implements Serializable {
    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEventoEquipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEvento")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Evento evento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEquipo")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Equipo equipo;

    public Long getIdEventoEquipo() {
        return idEventoEquipo;
    }

    public void setIdEventoEquipo(Long idEventoEquipo) {
        this.idEventoEquipo = idEventoEquipo;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}
