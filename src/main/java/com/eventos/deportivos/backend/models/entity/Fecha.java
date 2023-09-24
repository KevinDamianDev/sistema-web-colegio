package com.eventos.deportivos.backend.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "fechas")
public class Fecha implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFecha;

    private String nombreFecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idFase")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Fase fase;

    public Long getIdFecha() {
        return idFecha;
    }

    public void setIdFecha(Long idFecha) {
        this.idFecha = idFecha;
    }

    public String getNombreFecha() {
        return nombreFecha;
    }

    public void setNombreFecha(String nombreFecha) {
        this.nombreFecha = nombreFecha;
    }

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }
}
