package com.eventos.deportivos.backend.models.service;

import com.eventos.deportivos.backend.models.dao.IDueloDao;
import com.eventos.deportivos.backend.models.dao.IFechaDao;
import com.eventos.deportivos.backend.models.entity.Duelo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("duelo")
public class DueloServiceImpl implements ServiceEntity<Duelo, Long> , ServiceUnoAMuchos<Duelo, Long>{

    @Autowired
    private IDueloDao dueloDao;

    @Autowired
    private IFechaDao fechaDao;

    @Override
    public List<Duelo> listar() {
        return (List<Duelo>) dueloDao.findAll();
    }

    @Override
    public Duelo porId(Long id) {
        return dueloDao.findById(id).orElse(null);
    }

    @Override
    public Duelo guardar(Duelo entidad) {
        return dueloDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        dueloDao.deleteById(aLong);
    }

    @Override
    public boolean nombreExiste(String ent) {
        return false;
    }

    @Override
    public List<Duelo> listarEntitysByEntity(Long id) {
        return fechaDao.findDueloByFecha(id);
    }
}
