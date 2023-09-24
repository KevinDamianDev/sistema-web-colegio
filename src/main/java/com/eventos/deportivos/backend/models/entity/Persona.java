package com.eventos.deportivos.backend.models.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "personas")
public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;

    @Column(nullable = false)
    private String nombrePersona;
    private String apellidosPersonas;
    private int dniPersona;
    @Temporal(TemporalType.DATE)
    private Date fechaNacPersona;
    @Column(nullable = false, unique = true)
    private String coreoPersona;
    @Column(nullable = false, unique = true)
    private String usuarioPersona;
    private String passwordPersona;
    private boolean estadoPersona;

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getApellidosPersonas() {
        return apellidosPersonas;
    }

    public void setApellidosPersonas(String apellidosPersonas) {
        this.apellidosPersonas = apellidosPersonas;
    }

    public int getDniPersona() {
        return dniPersona;
    }

    public void setDniPersona(int dniPersona) {
        this.dniPersona = dniPersona;
    }

    public Date getFechaNacPersona() {
        return fechaNacPersona;
    }

    public void setFechaNacPersona(Date fechaNacPersona) {
        this.fechaNacPersona = fechaNacPersona;
    }

    public String getCoreoPersona() {
        return coreoPersona;
    }

    public void setCoreoPersona(String coreoPersona) {
        this.coreoPersona = coreoPersona;
    }

    public String getUsuarioPersona() {
        return usuarioPersona;
    }

    public void setUsuarioPersona(String usuarioPersona) {
        this.usuarioPersona = usuarioPersona;
    }

    public String getPasswordPersona() {
        return passwordPersona;
    }

    public void setPasswordPersona(String passwordPersona) {
        this.passwordPersona = passwordPersona;
    }

    public boolean isEstadoPersona() {
        return estadoPersona;
    }

    public void setEstadoPersona(boolean estadoPersona) {
        this.estadoPersona = estadoPersona;
    }
}
