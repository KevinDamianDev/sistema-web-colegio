package com.eventos.deportivos.backend.models.service;

import com.eventos.deportivos.backend.models.dao.IPersonaRolDao;
import com.eventos.deportivos.backend.models.entity.PersonaRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("personarol")
public class PersonaRolServiceImpl implements ServiceEntity<PersonaRol, Long>, ServiceEntityExisteDato<PersonaRol, Long> {

    @Autowired
    private IPersonaRolDao personaRolDao;

    @Override
    public List<PersonaRol> listar() {
        return (List<PersonaRol>) personaRolDao.findAll();
    }

    @Override
    public PersonaRol porId(Long id) {
        return null;
    }

    @Override
    public PersonaRol guardar(PersonaRol entidad) {
        return personaRolDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        personaRolDao.deleteById(aLong);
    }

    @Override
    public boolean nombreExiste(String ent) {
        return false;
    }

    @Override
    public PersonaRol existeRegistro(Long id1, Long id2) {
        return personaRolDao.buscarPorIdPersonaYIdRol(id1, id2);
    }
}
