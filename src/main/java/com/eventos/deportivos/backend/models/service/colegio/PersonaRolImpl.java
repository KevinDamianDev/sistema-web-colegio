package com.eventos.deportivos.backend.models.service.colegio;

import com.eventos.deportivos.backend.models.dao.colegio.IPersonaRolCDao;
import com.eventos.deportivos.backend.models.entity.colegio.PersonaRolC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Qualifier("personarol")
public class PersonaRolImpl  implements ServiceEntityC<PersonaRolC, Long>, ServiceExisteDato<PersonaRolC, Long> {

    @Autowired
    private IPersonaRolCDao personaRolCDao;

    @Override
    @Transactional(readOnly = true)
    public List<PersonaRolC> listar() {
        return (List<PersonaRolC>)personaRolCDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public PersonaRolC porId(Long id) {
        return personaRolCDao.findById(id).orElse(null);
    }

    @Override
    public PersonaRolC guardar(PersonaRolC entidad) {
        return personaRolCDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        personaRolCDao.deleteById(aLong);
    }

    @Override
    public PersonaRolC existeRegistro(Long id1, Long id2) {
        return personaRolCDao.buscarPorIdPersonaYIdRol(id1, id2);
    }
}
