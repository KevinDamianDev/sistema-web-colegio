package com.eventos.deportivos.backend.models.entity.colegio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "alumno_grado")
public class AlumnoGrado {
    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlumnoGrado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idGrado")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Grado grado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAlumno")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Alumno alumno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPeriodo")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Periodo periodo;

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Long getIdAlumnoGrado() {
        return idAlumnoGrado;
    }

    public void setIdAlumnoGrado(Long idAlumnoGrado) {
        this.idAlumnoGrado = idAlumnoGrado;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
}
