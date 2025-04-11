package com.eventos.deportivos.backend.models.entity.colegio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "curso_evaluacion")
public class CursoEvaluacion {

    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCursoEvaluacion;

    @Temporal(TemporalType.DATE)
    private Date fechaEvaluacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEvaluacion")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Evaluacion evaluacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCurso")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Curso curso;

    public Date getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(Date fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }

    public Long getIdCursoEvaluacion() {
        return idCursoEvaluacion;
    }

    public void setIdCursoEvaluacion(Long idCursoEvaluacion) {
        this.idCursoEvaluacion = idCursoEvaluacion;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
