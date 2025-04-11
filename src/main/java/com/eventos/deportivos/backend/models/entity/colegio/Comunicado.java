package com.eventos.deportivos.backend.models.entity.colegio;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "comunicado")
public class Comunicado {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComunicado;

    @Column( name = "titulo", nullable = false)
    @NotEmpty(message = "No puede estar vacío")
    private String titulo;

    @Column( name = "descripcion", nullable = false)
    @NotEmpty(message = "No puede estar vacío")
    private String descripcion;

    @Temporal(TemporalType.DATE)
    private Date fechaPublicacion;

    private boolean estadoComunicado;

    public boolean isEstadoComunicado() {
        return estadoComunicado;
    }

    public void setEstadoComunicado(boolean estadoComunicado) {
        this.estadoComunicado = estadoComunicado;
    }

    public Long getIdComunicado() {
        return idComunicado;
    }

    public void setIdComunicado(Long idComunicado) {
        this.idComunicado = idComunicado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
}
