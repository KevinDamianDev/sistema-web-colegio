package com.eventos.deportivos.backend.models.service.colegio;

import com.eventos.deportivos.backend.models.dao.colegio.IDocenteDao;
import com.eventos.deportivos.backend.models.entity.colegio.Docente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Qualifier("docente")
public class DocenteImpl  implements ServiceEntityC<Docente, Long>{

    @Autowired
    private IDocenteDao docenteDao;

    @Override
    @Transactional(readOnly = true)
    public List<Docente> listar() {
        return (List<Docente>)docenteDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Docente porId(Long id) {
        return docenteDao.findById(id).orElse(null);
    }

    @Override
    public Docente guardar(Docente entidad) {
        return docenteDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        docenteDao.deleteById(aLong);
    }
}
