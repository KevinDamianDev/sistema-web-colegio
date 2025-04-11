package com.eventos.deportivos.backend.models.service.colegio;

import com.eventos.deportivos.backend.models.dao.colegio.INotaDao;
import com.eventos.deportivos.backend.models.entity.colegio.Nota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Qualifier("nota")
public class NotaImpl  implements ServiceEntityC<Nota, Long>{

    @Autowired
    private INotaDao notaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Nota> listar() {
        return (List<Nota>)notaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Nota porId(Long id) {
        return notaDao.findById(id).orElse(null);
    }

    @Override
    public Nota guardar(Nota entidad) {
        return notaDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        notaDao.deleteById(aLong);
    }
}
