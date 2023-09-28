package com.eventos.deportivos.backend.models.service;

import com.eventos.deportivos.backend.models.dao.IEventoEquipoDao;
import com.eventos.deportivos.backend.models.entity.EventoEquipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("eventoequipo")
public class EventoEquipoServiceImpl implements ServiceEntity<EventoEquipo, Long>,ServiceEntityExisteDato<EventoEquipo, Long> {

    @Autowired
    private IEventoEquipoDao eventoEquipoDao;

    @Override
    public List<EventoEquipo> listar() {
        return (List<EventoEquipo>) eventoEquipoDao.findAll();
    }


    @Override
    public EventoEquipo guardar(EventoEquipo entidad) {
        return eventoEquipoDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        eventoEquipoDao.deleteById(aLong);
    }

    @Override
    public EventoEquipo existeRegistro(Long id1, Long id2) {
        return eventoEquipoDao.buscarPorIdEventoYIdEquipo(id1, id2);
    }

    @Override
    public EventoEquipo porId(Long id) {
        return null;
    }

    @Override
    public boolean nombreExiste(String ent) {
        return false;
    }
}
