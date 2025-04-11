package com.eventos.deportivos.backend.models.entity.colegio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "docente_curso")
public class DocenteCurso {

    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDocenteCurso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDocente")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Docente docente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCurso")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Curso curso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idGrado")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Grado grado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPeriodo")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Periodo periodo;

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Long getIdDocenteCurso() {
        return idDocenteCurso;
    }

    public void setIdDocenteCurso(Long idDocenteCurso) {
        this.idDocenteCurso = idDocenteCurso;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
