package com.eventos.deportivos.backend.models.service;

import com.eventos.deportivos.backend.models.dao.IEquipoGrupoDao;
import com.eventos.deportivos.backend.models.entity.EquipoGrupo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("equipogrupo")
public class EquipoGrupoServiceImpl implements ServiceEntity<EquipoGrupo, Long>, ServiceEntityExisteDato<EquipoGrupo, Long> {

    @Autowired
    private IEquipoGrupoDao equipoGrupoDao;

    @Override
    public List<EquipoGrupo> listar() {
        return (List<EquipoGrupo>) equipoGrupoDao.findAll();
    }

    @Override
    public EquipoGrupo guardar(EquipoGrupo entidad) {
        return equipoGrupoDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        equipoGrupoDao.deleteById(aLong);
    }

    @Override
    public EquipoGrupo existeRegistro(Long id1, Long id2) {
        return equipoGrupoDao.buscarPorIdEquipoYIdGrupo(id1, id2);
    }

    @Override
    public boolean nombreExiste(String ent) {
        return false;
    }

    @Override
    public EquipoGrupo porId(Long id) {
        return  equipoGrupoDao.findById(id).orElse(null);
    }

}
