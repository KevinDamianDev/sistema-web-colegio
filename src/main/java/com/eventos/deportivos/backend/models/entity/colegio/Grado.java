package com.eventos.deportivos.backend.models.entity.colegio;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
@Entity
@Table(name = "grado")
public class Grado {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGrado;

    @Column( name = "nombreGrado", nullable = false, length = 30)
    @NotEmpty(message = "No puede estar vacío")
    private String nombreGrado;

    @Column( name = "nombreSeccion", nullable = false, length = 30)
    @NotEmpty(message = "No puede estar vacío")
    private String nombreSeccion;

    public Long getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(Long idGrado) {
        this.idGrado = idGrado;
    }

    public String getNombreGrado() {
        return nombreGrado;
    }

    public void setNombreGrado(String nombreGrado) {
        this.nombreGrado = nombreGrado;
    }

    public String getNombreSeccion() {
        return nombreSeccion;
    }

    public void setNombreSeccion(String nombreSeccion) {
        this.nombreSeccion = nombreSeccion;
    }
}

