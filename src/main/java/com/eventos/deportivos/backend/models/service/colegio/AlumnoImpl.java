package com.eventos.deportivos.backend.models.service.colegio;

import com.eventos.deportivos.backend.models.dao.colegio.IAlumnoDao;
import com.eventos.deportivos.backend.models.entity.colegio.Alumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Qualifier("alumno")
public class AlumnoImpl implements ServiceEntityC<Alumno, Long>{

    @Autowired
    private IAlumnoDao alumnoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> listar() {
        return (List<Alumno>)alumnoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Alumno porId(Long id) {
        return alumnoDao.findById(id).orElse(null);
    }

    @Override
    public Alumno guardar(Alumno entidad) {
        return alumnoDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        alumnoDao.deleteById(aLong);
    }
}
