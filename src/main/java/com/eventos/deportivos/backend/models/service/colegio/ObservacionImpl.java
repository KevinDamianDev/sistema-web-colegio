package com.eventos.deportivos.backend.models.service.colegio;

import com.eventos.deportivos.backend.models.dao.colegio.IObservacionDao;
import com.eventos.deportivos.backend.models.entity.colegio.Observacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Qualifier("observacion")
public class ObservacionImpl  implements ServiceEntityC<Observacion, Long>{

    @Autowired
    private IObservacionDao observacionDao;

    @Override
    @Transactional(readOnly = true)
    public List<Observacion> listar() {
        return (List<Observacion>)observacionDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Observacion porId(Long id) {
        return observacionDao.findById(id).orElse(null);
    }

    @Override
    public Observacion guardar(Observacion entidad) {
        return observacionDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        observacionDao.deleteById(aLong);
    }
}
