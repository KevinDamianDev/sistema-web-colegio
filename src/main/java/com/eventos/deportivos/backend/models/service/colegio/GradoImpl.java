package com.eventos.deportivos.backend.models.service.colegio;

import com.eventos.deportivos.backend.models.dao.colegio.IGradoDao;
import com.eventos.deportivos.backend.models.entity.colegio.Grado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Qualifier("grado")
public class GradoImpl implements ServiceEntityC<Grado, Long>{

    @Autowired
    private IGradoDao gradoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Grado> listar() {
        return (List<Grado>)gradoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Grado porId(Long id) {
        return gradoDao.findById(id).orElse(null);
    }

    @Override
    public Grado guardar(Grado entidad) {
        return gradoDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        gradoDao.deleteById(aLong);
    }
}
