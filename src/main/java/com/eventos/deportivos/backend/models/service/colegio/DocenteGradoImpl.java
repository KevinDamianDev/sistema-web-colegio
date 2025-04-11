package com.eventos.deportivos.backend.models.service.colegio;

import com.eventos.deportivos.backend.models.dao.colegio.IDocenteGradoDao;
import com.eventos.deportivos.backend.models.entity.colegio.DocenteGrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Qualifier("docentegrado")
public class DocenteGradoImpl implements ServiceEntityC<DocenteGrado, Long>{

    @Autowired
    private IDocenteGradoDao docenteGradoDao;

    @Override
    @Transactional(readOnly = true)
    public List<DocenteGrado> listar() {
        return  (List<DocenteGrado>)docenteGradoDao.findAll();
    }

    @Override
    public DocenteGrado porId(Long id) {
        return docenteGradoDao.findById(id).orElse(null);
    }

    @Override
    public DocenteGrado guardar(DocenteGrado entidad) {
        return docenteGradoDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        docenteGradoDao.deleteById(aLong);
    }
}
