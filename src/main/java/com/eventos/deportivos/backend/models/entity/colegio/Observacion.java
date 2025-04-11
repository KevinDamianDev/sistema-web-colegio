package com.eventos.deportivos.backend.models.entity.colegio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "observacion")
public class Observacion {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idObservacion;

    @Column( name = "observacion", nullable = false, length = 300)
    @NotEmpty(message = "No puede estar vacío")
    private String observacion;

    @Temporal(TemporalType.DATE)
    private Date fechaObservacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAlumno")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Alumno alumno;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDocenteCurso")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private DocenteCurso docenteCurso;

    public Long getIdObservacion() {
        return idObservacion;
    }

    public void setIdObservacion(Long idObservacion) {
        this.idObservacion = idObservacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaObservacion() {
        return fechaObservacion;
    }

    public void setFechaObservacion(Date fechaObservacion) {
        this.fechaObservacion = fechaObservacion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public DocenteCurso getDocenteCurso() {
        return docenteCurso;
    }

    public void setDocenteCurso(DocenteCurso docenteCurso) {
        this.docenteCurso = docenteCurso;
    }
}
