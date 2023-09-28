package com.eventos.deportivos.backend.models.service;

import com.eventos.deportivos.backend.models.dao.IEventoDao;
import com.eventos.deportivos.backend.models.dao.IPersonaDao;
import com.eventos.deportivos.backend.models.entity.Evento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Qualifier("evento")
public  class EventoServiceImpl implements ServiceEntity<Evento, Long>, ServiceEntityEntity<Evento, Long>{

    @Autowired
    private IEventoDao eventoDao;

    @Autowired
    private IPersonaDao personaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Evento> listar() {
        return (List<Evento>) eventoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Evento porId(Long aLong) {
        return eventoDao.findById(aLong).orElse(null);
    }

    @Override
    @Transactional
    public Evento guardar(Evento entidad) {
        return eventoDao.save(entidad);
    }

    @Override
    @Transactional
    public void eliminar(Long aLong) {
        eventoDao.deleteById(aLong);
    }

    @Override
    public boolean nombreExiste(String ent) {
        return false;
    }

    @Override
    public List<Evento> listarByEntity(Long id) {
        return personaDao.findEventoByPersonaId(id);
    }
}
