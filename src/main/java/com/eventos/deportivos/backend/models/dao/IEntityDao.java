package com.eventos.deportivos.backend.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IEntityDao<T, ID> extends CrudRepository<T, ID> {
}
