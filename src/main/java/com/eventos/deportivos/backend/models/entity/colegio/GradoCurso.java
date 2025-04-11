package com.eventos.deportivos.backend.models.entity.colegio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "grado_curso")
public class GradoCurso {

    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGradoCurso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idGrado")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Grado grado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCurso")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Curso curso;


    public Long getIdGradoCurso() {
        return idGradoCurso;
    }

    public void setIdGradoCurso(Long idGradoCurso) {
        this.idGradoCurso = idGradoCurso;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
