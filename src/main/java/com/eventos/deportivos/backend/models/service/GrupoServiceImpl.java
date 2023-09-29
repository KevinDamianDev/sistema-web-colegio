package com.eventos.deportivos.backend.models.service;

import com.eventos.deportivos.backend.models.dao.IFaseDao;
import com.eventos.deportivos.backend.models.dao.IGrupoDao;
import com.eventos.deportivos.backend.models.entity.Grupo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("grupo")
public class GrupoServiceImpl implements ServiceEntity<Grupo, Long>, ServiceUnoAMuchos<Grupo, Long> {

    @Autowired
    private IGrupoDao grupoDao;

    @Autowired
    private IFaseDao faseDao;

    @Override
    public List<Grupo> listar() {
        return (List<Grupo>) grupoDao.findAll();
    }

    @Override
    public Grupo porId(Long id) {
        return grupoDao.findById(id).orElse(null);
    }

    @Override
    public Grupo guardar(Grupo entidad) {
        return grupoDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        grupoDao.deleteById(aLong);
    }

    @Override
    public boolean nombreExiste(String ent) {
        return false;
    }

    @Override
    public List<Grupo> listarEntitysByEntity(Long id) {
        return faseDao.findGrupoByFase(id);
    }
}
