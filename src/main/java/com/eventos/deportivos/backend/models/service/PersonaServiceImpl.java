package com.eventos.deportivos.backend.models.service;

import com.eventos.deportivos.backend.models.dao.IPersonaDao;
import com.eventos.deportivos.backend.models.entity.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("persona")
public class PersonaServiceImpl implements ServiceEntity<Persona, Long> {

    @Autowired
    private IPersonaDao personaDao;


    @Override
    public List<Persona> listar() {
        return (List<Persona>)personaDao.findAll();
    }

    @Override
    public Persona porId(Long id) {
        return personaDao.findById(id).orElse(null);
    }

    @Override
    public Persona guardar(Persona entidad) {
        return personaDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        personaDao.deleteById(aLong);
    }

    @Override
    public boolean nombreExiste(String ent) {
        return false;
    }


}
