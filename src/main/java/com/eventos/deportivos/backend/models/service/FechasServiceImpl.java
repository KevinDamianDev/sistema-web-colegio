package com.eventos.deportivos.backend.models.service;

import com.eventos.deportivos.backend.models.dao.IFaseDao;
import com.eventos.deportivos.backend.models.dao.IFechaDao;
import com.eventos.deportivos.backend.models.entity.Fecha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("fecha")
public class FechasServiceImpl implements ServiceEntity<Fecha, Long>, ServiceUnoAMuchos<Fecha, Long> {

     @Autowired
     private IFechaDao fechaDao;

    @Autowired
    private IFaseDao faseDao;

    @Override
    public List<Fecha> listar() {
        return (List<Fecha>) fechaDao.findAll();
    }

    @Override
    public Fecha porId(Long id) {
        return fechaDao.findById(id).orElse(null);
    }

    @Override
    public Fecha guardar(Fecha entidad) {
        return fechaDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        fechaDao.deleteById(aLong);
    }

    @Override
    public boolean nombreExiste(String ent) {
        return false;
    }

    @Override
    public List<Fecha> listarEntitysByEntity(Long id) {
        return faseDao.findFechaByFase(id);
    }
}
