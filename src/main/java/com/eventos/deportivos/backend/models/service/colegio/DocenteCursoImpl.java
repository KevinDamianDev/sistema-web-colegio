package com.eventos.deportivos.backend.models.service.colegio;

import com.eventos.deportivos.backend.models.dao.colegio.IDocenteCursoDao;
import com.eventos.deportivos.backend.models.entity.colegio.DocenteCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Qualifier("docentecurso")
public class DocenteCursoImpl  implements ServiceEntityC<DocenteCurso, Long>{

    @Autowired
    private IDocenteCursoDao docenteCursoDao;

    @Override
    @Transactional(readOnly = true)
    public List<DocenteCurso> listar() {
        return (List<DocenteCurso>)docenteCursoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public DocenteCurso porId(Long id) {
        return docenteCursoDao.findById(id).orElse(null);
    }

    @Override
    public DocenteCurso guardar(DocenteCurso entidad) {
        return docenteCursoDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        docenteCursoDao.deleteById(aLong);
    }
}
