package com.eventos.deportivos.backend.models.service.colegio;

import com.eventos.deportivos.backend.models.dao.colegio.IApoderadoAlumnoDao;
import com.eventos.deportivos.backend.models.entity.colegio.ApoderadoAlumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Qualifier("apoderadoalumno")
public class ApoderadoAlumnoImpl implements ServiceEntityC<ApoderadoAlumno, Long>{

    @Autowired
    private IApoderadoAlumnoDao apoderadoAlumnoDao;

    @Override
    @Transactional(readOnly = true)
    public List<ApoderadoAlumno> listar() {
        return (List<ApoderadoAlumno>)apoderadoAlumnoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ApoderadoAlumno porId(Long id) {
        return apoderadoAlumnoDao.findById(id).orElse(null);
    }

    @Override
    public ApoderadoAlumno guardar(ApoderadoAlumno entidad) {
        return apoderadoAlumnoDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        apoderadoAlumnoDao.deleteById(aLong);
    }
}
