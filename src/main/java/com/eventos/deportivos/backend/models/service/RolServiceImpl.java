package com.eventos.deportivos.backend.models.service;

import com.eventos.deportivos.backend.models.dao.IPersonaDao;
import com.eventos.deportivos.backend.models.dao.IRolDao;
import com.eventos.deportivos.backend.models.entity.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Qualifier("rol")
public class RolServiceImpl implements ServiceEntity<Rol, Long>, ServiceEntityEntity<Rol, Long> {

    @Autowired
    private IRolDao rolDao;

    @Autowired
    private IPersonaDao personaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Rol> listar() {
        return (List<Rol>)rolDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Rol porId(Long id) {
        return rolDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Rol guardar(Rol entidad) {
        return rolDao.save(entidad);
    }

    @Override
    @Transactional
    public void eliminar(Long aLong) {
        rolDao.deleteById(aLong);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean nombreExiste(String ent) {
        return rolDao.existsByNombreRoles(ent);
    }


    @Override
    public List<Rol> listarByEntity(Long id) {
        return personaDao.findRolesByPersonaId(id);
    }
}
