package com.eventos.deportivos.backend.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "duelo")
public class Duelo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDuelo;

    private String nombreDuelo;

    @Temporal(TemporalType.DATE)
    private Date fechaDuelo;

    private String lugarDuelo;

    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idFecha")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Fecha fecha;

    public String getNombreDuelo() {
        return nombreDuelo;
    }

    public void setNombreDuelo(String nombreDuelo) {
        this.nombreDuelo = nombreDuelo;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public Long getIdDuelo() {
        return idDuelo;
    }

    public void setIdDuelo(Long idDuelo) {
        this.idDuelo = idDuelo;
    }

    public Date getFechaDuelo() {
        return fechaDuelo;
    }

    public void setFechaDuelo(Date fechaDuelo) {
        this.fechaDuelo = fechaDuelo;
    }

    public String getLugarDuelo() {
        return lugarDuelo;
    }

    public void setLugarDuelo(String lugarDuelo) {
        this.lugarDuelo = lugarDuelo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
