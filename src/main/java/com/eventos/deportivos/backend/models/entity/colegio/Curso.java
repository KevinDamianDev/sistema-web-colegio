package com.eventos.deportivos.backend.models.entity.colegio;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
@Entity
@Table(name = "curso")
public class Curso {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCurso;

    @Column( name = "nombreCurso", nullable = false, length = 30)
    @NotEmpty(message = "No puede estar vacío")
    private String nombreCurso;

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }
}
