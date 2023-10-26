package com.eventos.deportivos.backend.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "equipo_estadistica")
public class EquipoEstadistica implements Serializable {
    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipoEstadistica;

    private int puntos;
    private int jugados;
    private int ganados;
    private int empatados;
    private int perdidos;
    private int golesFavor;
    private int golesContra;
    private int diferenciaGoles;
    private int penales;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEquipoGrupo")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private EquipoGrupo equipoGrupo;

    public Long getIdEquipoEstadistica() {
        return idEquipoEstadistica;
    }

    public void setIdEquipoEstadistica(Long idEquipoEstadistica) {
        this.idEquipoEstadistica = idEquipoEstadistica;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getJugados() {
        return jugados;
    }

    public void setJugados(int jugados) {
        this.jugados = jugados;
    }

    public int getGanados() {
        return ganados;
    }

    public void setGanados(int ganados) {
        this.ganados = ganados;
    }

    public int getEmpatados() {
        return empatados;
    }

    public void setEmpatados(int empatados) {
        this.empatados = empatados;
    }

    public int getPerdidos() {
        return perdidos;
    }

    public void setPerdidos(int perdidos) {
        this.perdidos = perdidos;
    }

    public int getGolesFavor() {
        return golesFavor;
    }

    public void setGolesFavor(int golesFavor) {
        this.golesFavor = golesFavor;
    }

    public int getGolesContra() {
        return golesContra;
    }

    public void setGolesContra(int golesContra) {
        this.golesContra = golesContra;
    }

    public EquipoGrupo getEquipoGrupo() {
        return equipoGrupo;
    }

    public void setEquipoGrupo(EquipoGrupo equipoGrupo) {
        this.equipoGrupo = equipoGrupo;
    }

    public int getDiferenciaGoles() {
        return diferenciaGoles;
    }

    public void setDiferenciaGoles(int diferenciaGoles) {
        this.diferenciaGoles = diferenciaGoles;
    }

    public int getPenales() {
        return penales;
    }

    public void setPenales(int penales) {
        this.penales = penales;
    }
}
