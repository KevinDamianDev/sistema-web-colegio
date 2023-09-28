package com.eventos.deportivos.backend.models.service;

import com.eventos.deportivos.backend.models.dao.IPersonaEventoDao;
import com.eventos.deportivos.backend.models.entity.PersonaEvento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("personaevento")
public class PersonaEventoServiceImpl implements ServiceEntity<PersonaEvento, Long> , ServiceEntityExisteDato<PersonaEvento, Long> {

    @Autowired
    private IPersonaEventoDao personaEventoDao;

    @Override
    public List<PersonaEvento> listar() {
        return (List<PersonaEvento>) personaEventoDao.findAll();
    }

    @Override
    public PersonaEvento guardar(PersonaEvento entidad) {
        return personaEventoDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        personaEventoDao.deleteById(aLong);
    }

    @Override
    public PersonaEvento existeRegistro(Long id1, Long id2) {
        return personaEventoDao.buscarPorIdPersonaYIdEvento(id1, id2);
    }

    @Override
    public boolean nombreExiste(String ent) {
        return false;
    }
    @Override

    public PersonaEvento porId(Long id) {
        return null;
    }

}
