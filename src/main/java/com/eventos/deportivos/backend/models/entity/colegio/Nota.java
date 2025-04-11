package com.eventos.deportivos.backend.models.entity.colegio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "nota")
public class Nota {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNota;

    @Column( name = "nota", nullable = false, length = 30)
    @NotEmpty(message = "No puede estar vacío")
    private String nota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAlumno")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Alumno alumno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCursoEvaluacion")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private CursoEvaluacion cursoEvaluacion;

    public Long getIdNota() {
        return idNota;
    }

    public void setIdNota(Long idNota) {
        this.idNota = idNota;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public CursoEvaluacion getCursoEvaluacion() {
        return cursoEvaluacion;
    }

    public void setCursoEvaluacion(CursoEvaluacion cursoEvaluacion) {
        this.cursoEvaluacion = cursoEvaluacion;
    }
}
