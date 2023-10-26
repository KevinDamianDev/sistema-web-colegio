package com.eventos.deportivos.backend.models.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "persona")
public class Persona  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;

    @Column(nullable = false)
    private String nombrePersona;
    @Column(nullable = false)
    private String apellidosPersonas;
    @NotNull
    @Column(nullable = false, unique =true)
    private int dniPersona;
    @Temporal(TemporalType.DATE)
    private Date fechaNacPersona;

    @Email
    @NotEmpty
    @Column(nullable = false, unique = true)
    private String correoPersona;
    @Column(nullable = false, unique = true)
    private String usuarioPersona;
    @Column(length = 60)
    private String passwordPersona;
    private boolean estadoPersona;


        @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
        @JoinTable(name ="usuarios_roles" , joinColumns = @JoinColumn(name = "usuario_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"), uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id", "role_id"})})
        private Set<Rol> roles = new HashSet<>();

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

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

    public String getCorreoPersona() {
        return correoPersona;
    }

    public void setCorreoPersona(String correoPersona) {
        this.correoPersona = correoPersona;
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
