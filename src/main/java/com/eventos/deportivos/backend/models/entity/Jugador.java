package com.eventos.deportivos.backend.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "jugador")
public class Jugador implements Serializable {
    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJugador;

    private String nombreJugador;
    @Column(unique = true)
    private int dni;
    @Temporal(TemporalType.DATE)
    private Date fechaNJugador;
    private int dorsalJugador;
    private String foto;

    public Long getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(Long idJugador) {
        this.idJugador = idJugador;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public Date getFechaNJugador() {
        return fechaNJugador;
    }

    public void setFechaNJugador(Date fechaNJugador) {
        this.fechaNJugador = fechaNJugador;
    }

    public int getDorsalJugador() {
        return dorsalJugador;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setDorsalJugador(int dorsalJugador) {
        this.dorsalJugador = dorsalJugador;
    }
}
