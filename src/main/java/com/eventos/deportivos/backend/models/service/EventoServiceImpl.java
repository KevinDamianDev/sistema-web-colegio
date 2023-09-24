package com.eventos.deportivos.backend.models.service;

import com.eventos.deportivos.backend.models.dao.IEventoDao;
import com.eventos.deportivos.backend.models.entity.Evento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("evento")
public  class EventoServiceImpl implements ServiceEntity<Evento, Long>{

    @Autowired
    private IEventoDao eventoDao;

    @Override
    public List<Evento> listar() {
        return (List<Evento>) eventoDao.findAll();
    }

    @Override
    public Evento porId(Long aLong) {
        return eventoDao.findById(aLong).orElse(null);
    }

    @Override
    public Evento guardar(Evento entidad) {
        return eventoDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        eventoDao.deleteById(aLong);
    }

    @Override
    public boolean nombreExiste(String ent) {
        return false;
    }

}
