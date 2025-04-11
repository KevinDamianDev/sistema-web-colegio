package com.eventos.deportivos.backend.models.entity.colegio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "horario")
public class Horario {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHorario;

    @Column( name = "dia", nullable = false, length = 30)
    @NotEmpty(message = "No puede estar vacío")
    private String dia;

    @Column( name = "horaInicio", nullable = false, length = 30)
    @NotEmpty(message = "No puede estar vacío")
    private String horaInicio;

    @Column( name = "horaFin", nullable = false, length = 30)
    @NotEmpty(message = "No puede estar vacío")
    private String horaFin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAlumnoGrado")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AlumnoGrado alumnoGrado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDocenteCurso")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private DocenteCurso docenteCurso;

    public Long getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Long idHorario) {
        this.idHorario = idHorario;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public AlumnoGrado getAlumnoGrado() {
        return alumnoGrado;
    }

    public void setAlumnoGrado(AlumnoGrado alumnoGrado) {
        this.alumnoGrado = alumnoGrado;
    }

    public DocenteCurso getDocenteCurso() {
        return docenteCurso;
    }

    public void setDocenteCurso(DocenteCurso docenteCurso) {
        this.docenteCurso = docenteCurso;
    }
}
