package com.eventos.deportivos.backend.models.service.colegio;


import com.eventos.deportivos.backend.models.dao.colegio.IApoderadoDao;
import com.eventos.deportivos.backend.models.entity.colegio.Apoderado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Qualifier("apoderado")
public class ApoderadoImpl implements ServiceEntityC<Apoderado, Long>{
    @Autowired
    private IApoderadoDao apoderadoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Apoderado> listar() {
        return (List<Apoderado>)apoderadoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Apoderado porId(Long id) {
        return apoderadoDao.findById(id).orElse(null);
    }

    @Override
    public Apoderado guardar(Apoderado entidad) {
        return apoderadoDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        apoderadoDao.deleteById(aLong);
    }
}
