package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.Duelo;
import com.eventos.deportivos.backend.models.entity.Fase;
import com.eventos.deportivos.backend.models.entity.Fecha;
import com.eventos.deportivos.backend.models.entity.Grupo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFaseDao extends CrudRepository<Fase, Long> {
    @Query("SELECT e FROM Fecha e WHERE e.fase.idFase = :faseId")
    List<Fecha> findFechaByFase(@Param("faseId") Long faseId);

    @Query("SELECT e FROM Grupo e WHERE e.fase.idFase = :faseId")
    List<Grupo> findGrupoByFase(@Param("faseId") Long faseId);
}
