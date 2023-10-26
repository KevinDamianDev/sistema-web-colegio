package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.EquipoEstadistica;
import com.eventos.deportivos.backend.models.entity.EquipoGrupo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEquipoEstadisticaDao extends CrudRepository<EquipoEstadistica, Long> {

    @Query("SELECT d FROM EquipoEstadistica d WHERE d.equipoGrupo.idEquipoGrupo=:idEquipoGrupo")
    List<EquipoEstadistica> getEquipoEstadisticaByEquipo(@Param("idEquipoGrupo") Long idEquipoGrupo);




}
