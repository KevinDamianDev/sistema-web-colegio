package com.eventos.deportivos.backend.models.service.colegio;

import com.eventos.deportivos.backend.models.dao.colegio.IAsistenciaDao;
import com.eventos.deportivos.backend.models.entity.colegio.Asistencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Qualifier("asistencia")
public class AsistenciaImpl implements ServiceEntityC<Asistencia, Long>{

    @Autowired
    private IAsistenciaDao asistenciaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Asistencia> listar() {
          return (List<Asistencia>)asistenciaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Asistencia porId(Long id) {
        return asistenciaDao.findById(id).orElse(null);
    }

    @Override
    public Asistencia guardar(Asistencia entidad) {
        return asistenciaDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        asistenciaDao.deleteById(aLong);
    }
}
