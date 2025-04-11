package com.eventos.deportivos.backend.models.service.colegio;

import com.eventos.deportivos.backend.models.dao.colegio.ICursoDao;
import com.eventos.deportivos.backend.models.entity.colegio.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Qualifier("curso")
public class CursoImpl implements ServiceEntityC<Curso, Long>{

    @Autowired
    private ICursoDao cursoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Curso> listar() {
        return (List<Curso>)cursoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Curso porId(Long id) {
        return cursoDao.findById(id).orElse(null);
    }

    @Override
    public Curso guardar(Curso entidad) {
        return cursoDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        cursoDao.deleteById(aLong);
    }
}
