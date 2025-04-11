package com.eventos.deportivos.backend.models.service.colegio;

import com.eventos.deportivos.backend.models.dao.colegio.IAlumnoGradoDao;
import com.eventos.deportivos.backend.models.entity.colegio.AlumnoGrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Qualifier("alumnogrado")
public class AlumnoGradoImpl implements ServiceEntityC<AlumnoGrado, Long> {

    @Autowired
    private IAlumnoGradoDao alumnogradoDao;

    @Override
    @Transactional(readOnly = true)
    public List<AlumnoGrado> listar() {
        return  (List<AlumnoGrado>)alumnogradoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public AlumnoGrado porId(Long id) {
        return alumnogradoDao.findById(id).orElse(null);
    }

    @Override
    public AlumnoGrado guardar(AlumnoGrado entidad) {
        return alumnogradoDao.save(entidad);
    }

    @Override
    public void eliminar(Long id) {
        alumnogradoDao.deleteById(id);
    }
}
