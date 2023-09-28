package com.eventos.deportivos.backend.models.service;

import com.eventos.deportivos.backend.models.dao.IJugadorDao;
import com.eventos.deportivos.backend.models.entity.Jugador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("jugador")
public class JugadorServiceImpl implements ServiceEntity<Jugador, Long> {

    @Autowired
    private IJugadorDao jugadorDao;

    @Override
    public List<Jugador> listar() {
        return (List<Jugador>) jugadorDao.findAll();
    }

    @Override
    public Jugador porId(Long id) {
        return jugadorDao.findById(id).orElse(null);
    }

    @Override
    public Jugador guardar(Jugador entidad) {
        return jugadorDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        jugadorDao.deleteById(aLong);
    }

    @Override
    public boolean nombreExiste(String ent) {
        return false;
    }
}
