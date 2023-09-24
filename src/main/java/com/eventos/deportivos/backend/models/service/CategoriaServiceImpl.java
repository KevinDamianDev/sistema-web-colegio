package com.eventos.deportivos.backend.models.service;

import com.eventos.deportivos.backend.models.dao.ICategoriaDao;
import com.eventos.deportivos.backend.models.entity.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Qualifier("categoria")
public  class CategoriaServiceImpl implements ServiceEntity<Categoria, Long> {

    @Autowired
    private ICategoriaDao categoriaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> listar() {
        return (List<Categoria>)categoriaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria porId(Long id) {
        return categoriaDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Categoria guardar(Categoria entidad) {
        return categoriaDao.save(entidad);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        categoriaDao.deleteById(id);
    }

    @Override
    @Transactional
    public boolean nombreExiste(String ent) {
        return categoriaDao.existsByNombreCategoria(ent);
    }

}
