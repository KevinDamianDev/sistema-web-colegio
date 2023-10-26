package com.eventos.deportivos.backend.models.service;

import com.eventos.deportivos.backend.models.dao.IPersonaDao;
import com.eventos.deportivos.backend.models.entity.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaServiceSpring implements UserDetailsService, IPersonaServiceSpring {

    private Logger log = LoggerFactory.getLogger(PersonaServiceSpring.class);

    @Autowired
    private IPersonaDao personaDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Persona persona = personaDao.findByUserName(username);

        if (persona == null){
            log.error("Usuario no existe");
            throw new UsernameNotFoundException("Usuario no existe en el sistema");
        }

        List<GrantedAuthority> authorities = persona.getRoles().
                stream().
                map(role -> new SimpleGrantedAuthority(role.getNombreRoles()))
                      .collect(Collectors.toList());

        return new User(persona.getUsuarioPersona(), persona.getPasswordPersona(), persona.isEstadoPersona(),
                true, true, true, authorities);
    }

    @Override
    public Persona findByUserName(String usuarioPersona) {
        return  personaDao.findByUserName(usuarioPersona);
    }
}
