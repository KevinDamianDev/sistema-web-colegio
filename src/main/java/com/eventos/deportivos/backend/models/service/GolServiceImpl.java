package com.eventos.deportivos.backend.models.service;

import com.eventos.deportivos.backend.models.dao.IGolDao;
import com.eventos.deportivos.backend.models.entity.Gol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("gol")
public class GolServiceImpl implements ServiceEntity<Gol, Long>,ServiceUnoAMuchos<Gol,Long>{

    @Autowired
    private IGolDao golDao;

    @Override
    public List<Gol> listar() {
        return (List<Gol>) golDao.findAll();
    }

    @Override
    public Gol guardar(Gol entidad) {
        return golDao.save(entidad);
    }

    @Override
    public void eliminar(Long aLong) {
        golDao.deleteById(aLong);
    }
   /* @Override
    public Gol existeRegistro(Long id1, Long id2) {
        return golDao.buscarPorIdDueloYIdEquipoJugador(id1,id2);
    }*/
    @Override
    public List<Gol> listarEntitysByEntity(Long id) {
        return golDao.findGolByIdDuelo(id);
    }

    @Override
    public boolean nombreExiste(String ent) {
        return false;
    }

    @Override
    public Gol porId(Long id) {
        return golDao.findById(id).orElse(null);
    }



}
