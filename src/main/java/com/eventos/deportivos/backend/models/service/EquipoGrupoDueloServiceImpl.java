package com.eventos.deportivos.backend.models.service;

import com.eventos.deportivos.backend.models.dao.IEquipoGrupoDueloDao;
import com.eventos.deportivos.backend.models.entity.EquipoGrupoDuelo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("equipogrupoduelo")
public class EquipoGrupoDueloServiceImpl implements ServiceEntity<EquipoGrupoDuelo, Long>, ServiceEntityExisteDato<EquipoGrupoDuelo, Long>
, ServiceUnoAMuchos<EquipoGrupoDuelo, Long>{

    @Autowired
    private IEquipoGrupoDueloDao iEquipoGrupoDueloDao;

    @Override
    public List<EquipoGrupoDuelo> listar() {
        return (List<EquipoGrupoDuelo>) iEquipoGrupoDueloDao.findAll();
    }


    @Override
    public EquipoGrupoDuelo guardar(EquipoGrupoDuelo entidad) {
        return iEquipoGrupoDueloDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        iEquipoGrupoDueloDao.deleteById(aLong);
    }

    @Override
    public boolean nombreExiste(String ent) {
        return false;
    }

    @Override
    public EquipoGrupoDuelo porId(Long id) {
        return null;
    }

    @Override
    public EquipoGrupoDuelo existeRegistro(Long id1, Long id2) {
        return iEquipoGrupoDueloDao.buscarPorIdDueloYIdEquipoGrupo(id1, id2);
    }

    @Override
    public List<EquipoGrupoDuelo> listarEntitysByEntity(Long id) {
        return iEquipoGrupoDueloDao.findEquipoGrupoDueloByIdDuelo(id);
    }
}
