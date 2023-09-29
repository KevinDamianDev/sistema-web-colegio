package com.eventos.deportivos.backend.models.service;

import com.eventos.deportivos.backend.models.dao.IEventoDao;
import com.eventos.deportivos.backend.models.dao.IFaseDao;
import com.eventos.deportivos.backend.models.entity.Fase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("fase")
public class FaseServiceImpl implements ServiceEntity<Fase, Long>, ServiceUnoAMuchos<Fase, Long>{

    @Autowired
    private IFaseDao faseDao;

    @Autowired
    private IEventoDao eventoDao;

    @Override
    public List<Fase> listar() {
        return (List<Fase>) faseDao.findAll();
    }

    @Override
    public Fase porId(Long id) {
        return faseDao.findById(id).orElse(null);
    }

    @Override
    public Fase guardar(Fase entidad) {
        return faseDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        faseDao.deleteById(aLong);
    }

    @Override
    public boolean nombreExiste(String ent) {
        return false;
    }

    @Override
    public List<Fase> listarEntitysByEntity(Long id) {
        return eventoDao.findFaseByEvento(id);
    }
}
