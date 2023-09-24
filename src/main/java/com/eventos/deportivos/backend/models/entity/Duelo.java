package com.eventos.deportivos.backend.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "duelos")
public class Duelo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDuelo;

    @Temporal(TemporalType.DATE)
    private Date fechaDuelo;

    private String lugarDuelo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idFecha")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Fecha fecha;

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
}
