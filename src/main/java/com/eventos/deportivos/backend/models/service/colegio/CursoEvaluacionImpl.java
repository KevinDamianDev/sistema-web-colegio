package com.eventos.deportivos.backend.models.service.colegio;

import com.eventos.deportivos.backend.models.dao.colegio.ICursoEvaluacionDao;
import com.eventos.deportivos.backend.models.entity.colegio.CursoEvaluacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Qualifier("cursoevaluacion")
public class CursoEvaluacionImpl implements ServiceEntityC<CursoEvaluacion, Long>{
    @Autowired
    private ICursoEvaluacionDao cursoEvaluacionDao;

    @Override
    @Transactional(readOnly = true)
    public List<CursoEvaluacion> listar() {
        return (List<CursoEvaluacion>)cursoEvaluacionDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public CursoEvaluacion porId(Long id) {
        return cursoEvaluacionDao.findById(id).orElse(null);
    }

    @Override
    public CursoEvaluacion guardar(CursoEvaluacion entidad) {
        return cursoEvaluacionDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        cursoEvaluacionDao.deleteById(aLong);
    }
}
