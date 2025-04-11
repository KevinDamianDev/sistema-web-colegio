package com.eventos.deportivos.backend.models.service.colegio;

import com.eventos.deportivos.backend.models.dao.colegio.IHorarioDao;
import com.eventos.deportivos.backend.models.entity.colegio.Horario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Qualifier("horario")
public class HorarioImpl  implements ServiceEntityC<Horario, Long>{

    @Autowired
    private IHorarioDao horarioDao;

    @Override
    @Transactional(readOnly = true)
    public List<Horario> listar() {
        return (List<Horario>)horarioDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Horario porId(Long id) {
        return horarioDao.findById(id).orElse(null);
    }

    @Override
    public Horario guardar(Horario entidad) {
        return horarioDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        horarioDao.deleteById(aLong);
    }
}
