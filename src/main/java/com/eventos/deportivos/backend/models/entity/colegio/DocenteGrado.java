package com.eventos.deportivos.backend.models.entity.colegio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "docente_grado")
public class DocenteGrado {

    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDocenteGrado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idGrado")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Grado grado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDocente")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Docente docente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPeriodo")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Periodo periodo;

    public Long getIdDocenteGrado() {
        return idDocenteGrado;
    }

    public void setIdDocenteGrado(Long idDocenteGrado) {
        this.idDocenteGrado = idDocenteGrado;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }
}
