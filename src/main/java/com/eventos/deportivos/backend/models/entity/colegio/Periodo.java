package com.eventos.deportivos.backend.models.entity.colegio;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
@Entity
@Table(name = "periodo")
public class Periodo {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPeriodo;

    @Column( name = "nombrePeriodo", nullable = false)
    @NotEmpty(message = "No puede estar vacío")
    private String nombrePeriodo;

    private boolean estadoPeriodo;

    public Long getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Long idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public String getNombrePeriodo() {
        return nombrePeriodo;
    }

    public void setNombrePeriodo(String nombrePeriodo) {
        this.nombrePeriodo = nombrePeriodo;
    }

    public boolean isEstadoPeriodo() {
        return estadoPeriodo;
    }

    public void setEstadoPeriodo(boolean estadoPeriodo) {
        this.estadoPeriodo = estadoPeriodo;
    }
}
