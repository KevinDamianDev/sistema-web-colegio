package com.eventos.deportivos.backend.models.entity.colegio;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "rol")
@Entity
public class RolC implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;

    @Column(unique = true, length = 20)
    private String nombreRoles;

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public String getNombreRoles() {
        return nombreRoles;
    }

    public void setNombreRoles(String nombreRoles) {
        this.nombreRoles = nombreRoles;
    }
}
