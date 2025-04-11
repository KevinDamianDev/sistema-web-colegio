package com.eventos.deportivos.backend.models.entity.colegio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "alumno_curso")
public class AlumnoCurso {

    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlumnoCurso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAlumno")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Alumno alumno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCurso")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Curso curso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPeriodo")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Periodo periodo;

    public Long getIdAlumnoCurso() {
        return idAlumnoCurso;
    }

    public void setIdAlumnoCurso(Long idAlumnoCurso) {
        this.idAlumnoCurso = idAlumnoCurso;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }
}
