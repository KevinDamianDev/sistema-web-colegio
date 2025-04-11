package com.eventos.deportivos.backend.models.service.colegio;

import com.eventos.deportivos.backend.models.dao.colegio.IComunicadoDao;
import com.eventos.deportivos.backend.models.entity.colegio.Comunicado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Qualifier("comunicado")
public class ComunicadoImpl implements ServiceEntityC<Comunicado, Long>{

    @Autowired
    private IComunicadoDao comunicadoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Comunicado> listar() {
        return (List<Comunicado>)comunicadoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Comunicado porId(Long id) {
        return comunicadoDao.findById(id).orElse(null);
    }

    @Override
    public Comunicado guardar(Comunicado entidad) {
        return comunicadoDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        comunicadoDao.deleteById(aLong);
    }
}
