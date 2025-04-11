package com.eventos.deportivos.backend.models.entity.colegio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "apoderado_alumno")
public class ApoderadoAlumno {

    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idApoderadoAlumno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idApoderado")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Apoderado apoderado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAlumno")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Alumno alumno;

    public Long getIdApoderadoAlumno() {
        return idApoderadoAlumno;
    }

    public void setIdApoderadoAlumno(Long idApoderadoAlumno) {
        this.idApoderadoAlumno = idApoderadoAlumno;
    }

    public Apoderado getApoderado() {
        return apoderado;
    }

    public void setApoderado(Apoderado apoderado) {
        this.apoderado = apoderado;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
}
