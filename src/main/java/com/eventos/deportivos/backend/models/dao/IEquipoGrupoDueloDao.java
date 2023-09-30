package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.EquipoGrupoDuelo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEquipoGrupoDueloDao extends CrudRepository<EquipoGrupoDuelo, Long> {
    @Query("SELECT pr FROM EquipoGrupoDuelo pr WHERE pr.duelo.idDuelo = :idDuelo AND pr.equipoGrupo.idEquipoGrupo = :idEquipoGrupo")
    EquipoGrupoDuelo buscarPorIdDueloYIdEquipoGrupo(@Param("idDuelo") Long idDuelo, @Param("idEquipoGrupo") Long idEquipoGrupo);

    @Query("SELECT egd FROM EquipoGrupoDuelo egd WHERE egd.duelo.idDuelo = :idDuelo")
    List<EquipoGrupoDuelo> findEquipoGrupoDueloByIdDuelo (@Param("idDuelo") Long idDuelo);
}
