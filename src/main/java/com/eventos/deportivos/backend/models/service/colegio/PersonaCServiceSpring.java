package com.eventos.deportivos.backend.models.service.colegio;

import com.eventos.deportivos.backend.models.dao.colegio.IPersonaCDao;
import com.eventos.deportivos.backend.models.entity.colegio.PersonaC;
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
public class PersonaCServiceSpring implements UserDetailsService, IPersonaCServiceSpring {

    private Logger log = LoggerFactory.getLogger(PersonaCServiceSpring.class);

    @Autowired
    private IPersonaCDao personaDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        PersonaC persona = personaDao.findUserName(username);

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
    public PersonaC findByUserName(String usuarioPersona) {
        return  personaDao.findUserName(usuarioPersona);
    }
}
