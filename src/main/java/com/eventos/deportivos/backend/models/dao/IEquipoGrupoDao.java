package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.EquipoGrupo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEquipoGrupoDao extends CrudRepository<EquipoGrupo, Long> {
    @Query("SELECT pr FROM EquipoGrupo pr WHERE pr.equipo.idEquipo = :idEquipo AND pr.grupo.idGrupo = :idGrupo")
    EquipoGrupo buscarPorIdEquipoYIdGrupo(@Param("idEquipo") Long idEquipo, @Param("idGrupo") Long idGrupo);


    @Query("SELECT d FROM EquipoGrupo d WHERE d.grupo.fase.idFase = :idFase")
    List<EquipoGrupo> getEquipoGrupoByFase(@Param("idFase") Long idFase);

    @Query("SELECT d FROM EquipoGrupo d WHERE d.grupo.idGrupo= :idGrupo")
    List<EquipoGrupo> getEquipoGrupoByGrupo(@Param("idGrupo") Long idGrupo);

}
