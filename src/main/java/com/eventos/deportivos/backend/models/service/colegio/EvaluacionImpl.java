package com.eventos.deportivos.backend.models.service.colegio;

import com.eventos.deportivos.backend.models.dao.colegio.IEvaluacionDao;
import com.eventos.deportivos.backend.models.entity.colegio.Evaluacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Qualifier("evaluacion")
public class EvaluacionImpl implements ServiceEntityC<Evaluacion, Long>{

    @Autowired
    private IEvaluacionDao evaluacionDao;

    @Override
    @Transactional(readOnly = true)
    public List<Evaluacion> listar() {
        return (List<Evaluacion>)evaluacionDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Evaluacion porId(Long id) {
        return evaluacionDao.findById(id).orElse(null);
    }

    @Override
    public Evaluacion guardar(Evaluacion entidad) {
        return evaluacionDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        evaluacionDao.deleteById(aLong);
    }
}
