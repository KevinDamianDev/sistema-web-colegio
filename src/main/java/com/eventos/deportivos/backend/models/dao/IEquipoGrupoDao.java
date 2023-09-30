package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.EquipoGrupo;
import com.eventos.deportivos.backend.models.entity.EventoEquipo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IEquipoGrupoDao extends CrudRepository<EquipoGrupo, Long> {
    @Query("SELECT pr FROM EquipoGrupo pr WHERE pr.equipo.idEquipo = :idEquipo AND pr.grupo.idGrupo = :idGrupo")
    EquipoGrupo buscarPorIdEquipoYIdGrupo(@Param("idEquipo") Long idEquipo, @Param("idGrupo") Long idGrupo);
}
