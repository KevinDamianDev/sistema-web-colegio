package com.eventos.deportivos.backend.models.service.colegio;

import com.eventos.deportivos.backend.models.dao.colegio.IPersonaCDao;
import com.eventos.deportivos.backend.models.entity.colegio.PersonaC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Qualifier("persona")
public class PersonaCImpl implements ServiceEntityC<PersonaC, Long>, ServiceListEntity<PersonaC, Long> {

    @Autowired
    private IPersonaCDao personaCDao;

    @Override
    @Transactional(readOnly = true)
    public List<PersonaC> listar() {
        return (List<PersonaC>)personaCDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public PersonaC porId(Long id) {
        return personaCDao.findById(id).orElse(null);
    }

    @Override
    public PersonaC guardar(PersonaC entidad) {
        return personaCDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        personaCDao.deleteById(aLong);
    }
    // LISTA LOS DOCENTES
    @Override
    public List<PersonaC> listarData() {
         return (List<PersonaC>)personaCDao.findAllDocentes();
    }
}
