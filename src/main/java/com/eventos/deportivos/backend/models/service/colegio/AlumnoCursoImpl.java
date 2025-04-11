package com.eventos.deportivos.backend.models.service.colegio;

import com.eventos.deportivos.backend.models.dao.colegio.IAlumnoCursoDao;
import com.eventos.deportivos.backend.models.entity.colegio.AlumnoCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Qualifier("alumnocurso")
public class AlumnoCursoImpl implements ServiceEntityC<AlumnoCurso, Long> {

    @Autowired
    private IAlumnoCursoDao alumnoCursoDao;


    @Override
    @Transactional(readOnly = true)
    public List<AlumnoCurso> listar() {
        return (List<AlumnoCurso>)alumnoCursoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public AlumnoCurso porId(Long id) {
        return alumnoCursoDao.findById(id).orElse(null);
    }

    @Override
    public AlumnoCurso guardar(AlumnoCurso entidad) {
        return alumnoCursoDao.save(entidad);
    }

    @Override
    public void eliminar(Long id) {
        alumnoCursoDao.deleteById(id);
    }

}
