package com.eventos.deportivos.backend.models.service.colegio;

import com.eventos.deportivos.backend.models.dao.colegio.IRolCDao;
import com.eventos.deportivos.backend.models.entity.colegio.RolC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Qualifier("rol")
public class RolImpl implements ServiceEntityC<RolC, Long> {

    @Autowired
    private IRolCDao rolCDao;

    @Override
    @Transactional(readOnly = true)
    public List<RolC> listar() {
        return (List<RolC>)rolCDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public RolC porId(Long id) {
        return rolCDao.findById(id).orElse(null);
    }

    @Override
    public RolC guardar(RolC entidad) {
        return rolCDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        rolCDao.deleteById(aLong);
    }
}
