package com.eventos.deportivos.backend.models.service;

import com.eventos.deportivos.backend.models.dao.IEquipoEstadisticaDao;
import com.eventos.deportivos.backend.models.dao.IGrupoDao;
import com.eventos.deportivos.backend.models.entity.EquipoEstadistica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("equipoestadistica")
public class EquipoEstadisticaServiceImpl implements ServiceEntity<EquipoEstadistica, Long>, ServiceEntityEntity<EquipoEstadistica, Long>{

    @Autowired
    private IEquipoEstadisticaDao dao;

     @Autowired
     private IGrupoDao grupoDao;

    @Override
    public List<EquipoEstadistica> listar() {
        return (List<EquipoEstadistica>) dao.findAll();
    }

    @Override
    public EquipoEstadistica porId(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public EquipoEstadistica guardar(EquipoEstadistica entidad) {
        return dao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        dao.deleteById(aLong);
    }

    @Override
    public boolean nombreExiste(String ent) {
        return false;
    }

    @Override
    public List<EquipoEstadistica> listarByEntity(Long id) {
        return grupoDao.findByGrupoId(id);
    }
}
