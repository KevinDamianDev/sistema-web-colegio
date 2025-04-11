package com.eventos.deportivos.backend.models.service.colegio;

import com.eventos.deportivos.backend.models.dao.colegio.IAlumnoCursoDao;
import com.eventos.deportivos.backend.models.dao.colegio.IPeriodoDao;
import com.eventos.deportivos.backend.models.entity.colegio.AlumnoCurso;
import com.eventos.deportivos.backend.models.entity.colegio.Periodo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Qualifier("periodo")
public class PeriodoImpl implements ServiceEntityC<Periodo, Long> {

    @Autowired
    private IPeriodoDao periodoDao;


    @Override
    @Transactional(readOnly = true)
    public List<Periodo> listar() {
        return (List<Periodo>)periodoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Periodo porId(Long id) {
        return periodoDao.findById(id).orElse(null);
    }

    @Override
    public Periodo guardar(Periodo entidad) {
        return periodoDao.save(entidad);
    }

    @Override
    public void eliminar(Long id) {
        periodoDao.deleteById(id);
    }

}
