package com.eventos.deportivos.backend.models.service;

import com.eventos.deportivos.backend.models.dao.IEquipoDao;
import com.eventos.deportivos.backend.models.dao.IEventoDao;
import com.eventos.deportivos.backend.models.dao.IGrupoDao;
import com.eventos.deportivos.backend.models.entity.Equipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("equipo")
public class EquipoServiceImpl implements ServiceEntity<Equipo, Long>, ServiceEntityEntity<Equipo, Long>
, ServiceEquipoGrupo<Equipo, Long> {

    @Autowired
    private IEquipoDao equipoDao;

    @Autowired
    private IEventoDao eventoDao;

    @Autowired
    private IGrupoDao grupoDao;

    @Override
    public List<Equipo> listar() {
        return (List<Equipo>) equipoDao.findAll();
    }

    @Override
    public Equipo porId(Long id) {
        return equipoDao.findById(id).orElse(null);
    }

    @Override
    public Equipo guardar(Equipo entidad) {
        return equipoDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        equipoDao.deleteById(aLong);
    }

    @Override
    public boolean nombreExiste(String ent) {
        return false;
    }
    // MUESTRA LOS EQUIPOS QUE PERTENECEN A UN EVENTO.
    @Override
    public List<Equipo> listarByEntity(Long id) {
        return eventoDao.findEquipoByEventoId(id);
    }

    @Override
    public List<Equipo> listarEquipoByGrupo(Long id) {
        return grupoDao.findEquipoByGrupoId(id);
    }
}
