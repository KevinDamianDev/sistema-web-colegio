package com.eventos.deportivos.backend.models.service;

import com.eventos.deportivos.backend.models.dao.ICategoriaDao;
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
public  class EventoServiceImpl implements ServiceEntity<Evento, Long>, ServiceEntityEntity<Evento, Long>, ServiceUnoAMuchos<Evento, Long>{

    @Autowired
    private IEventoDao eventoDao;

    @Autowired
    private IPersonaDao personaDao;

    @Autowired
    private ICategoriaDao categoriaDao;

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
    // MUESTRA LOS EVENTOS QUE PERTENECEN A UNA CATEGORIA
    @Override
    public List<Evento> listarEntitysByEntity(Long id) {
        return categoriaDao.findByCategoria(id);
    }
}
