package com.eventos.deportivos.backend.models.service;

import com.eventos.deportivos.backend.models.dao.IEquipoJugadorDao;
import com.eventos.deportivos.backend.models.entity.EquipoJugador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("equipojugador")
public class EquipoJugadorServiceImpl implements ServiceEntity<EquipoJugador, Long>,
        ServiceEntityExisteDato<EquipoJugador, Long>, ServiceEntityEntity<EquipoJugador, Long> {

    @Autowired
    private IEquipoJugadorDao equipoJugadorDao;

    @Override
    public List<EquipoJugador> listar() {
        return (List<EquipoJugador>) equipoJugadorDao.findAll();
    }


    @Override
    public EquipoJugador guardar(EquipoJugador entidad) {
        return equipoJugadorDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        equipoJugadorDao.deleteById(aLong);
    }

    @Override
    public EquipoJugador existeRegistro(Long id1, Long id2) {
        return equipoJugadorDao.buscarPorIdEventoYIdJugador(id1, id2);
    }

    @Override
    public boolean nombreExiste(String ent) {
        return false;
    }

    @Override
    public EquipoJugador porId(Long id) {
        return equipoJugadorDao.findById(id).orElse(null);
    }

    @Override
    public List<EquipoJugador> listarByEntity(Long id) {
        return equipoJugadorDao.getEquipoJugadorByEquipo(id);
    }
}
