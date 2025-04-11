package com.eventos.deportivos.backend.models.entity.colegio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "asistencia")
public class Asistencia {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAsistencia;

    @Temporal(TemporalType.DATE)
    private Date fechaAsistencia;

    @Column( name = "estadoAsistencia", nullable = false, length = 30)
    @NotEmpty(message = "No puede estar vacío")
    private String estadoAsistencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAlumnoCurso")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AlumnoCurso alumnoCurso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDocenteCurso")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private DocenteCurso docenteCurso;


    public Long getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(Long idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Date getFechaAsistencia() {
        return fechaAsistencia;
    }

    public void setFechaAsistencia(Date fechaAsistencia) {
        this.fechaAsistencia = fechaAsistencia;
    }

    public String getEstadoAsistencia() {
        return estadoAsistencia;
    }

    public void setEstadoAsistencia(String estadoAsistencia) {
        this.estadoAsistencia = estadoAsistencia;
    }

    public AlumnoCurso getAlumnoCurso() {
        return alumnoCurso;
    }

    public void setAlumnoCurso(AlumnoCurso alumnoCurso) {
        this.alumnoCurso = alumnoCurso;
    }

    public DocenteCurso getDocenteCurso() {
        return docenteCurso;
    }

    public void setDocenteCurso(DocenteCurso docenteCurso) {
        this.docenteCurso = docenteCurso;
    }
}
