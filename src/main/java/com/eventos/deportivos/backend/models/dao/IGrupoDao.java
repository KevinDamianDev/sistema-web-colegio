package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.Equipo;
import com.eventos.deportivos.backend.models.entity.EquipoEstadistica;
import com.eventos.deportivos.backend.models.entity.Grupo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IGrupoDao extends CrudRepository<Grupo, Long> {
    @Query("SELECT pr.equipo FROM EquipoGrupo pr WHERE pr.grupo.idGrupo = :id")
    List<Equipo> findEquipoByGrupoId(@Param("id") Long id);

    @Query("SELECT e FROM EquipoEstadistica e JOIN e.equipoGrupo. grupo g WHERE g.idGrupo = :idGrupo")
    List<EquipoEstadistica> findByGrupoId(@Param("idGrupo") Long idGrupo);
}
