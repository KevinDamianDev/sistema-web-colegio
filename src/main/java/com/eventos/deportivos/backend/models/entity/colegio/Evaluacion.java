package com.eventos.deportivos.backend.models.entity.colegio;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "evaluacion")
public class Evaluacion {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvaluacion;

    @Column( name = "nombreEvaluacion", nullable = false, length = 30)
    @NotEmpty(message = "No puede estar vacío")
    private String nombreEvaluacion;

    public Long getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(Long idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public String getNombreEvaluacion() {
        return nombreEvaluacion;
    }

    public void setNombreEvaluacion(String nombreEvaluacion) {
        this.nombreEvaluacion = nombreEvaluacion;
    }
}
