package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.EquipoGrupo;
import com.eventos.deportivos.backend.models.entity.EquipoJugador;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEquipoJugadorDao extends CrudRepository<EquipoJugador, Long> {

    @Query("SELECT pr FROM EquipoJugador pr WHERE pr.equipo.idEquipo = :idEquipo AND pr.jugador.idJugador = :idJugador")
    EquipoJugador buscarPorIdEventoYIdJugador(@Param("idEquipo") Long idEquipo, @Param("idJugador") Long idJugador);

    @Query("SELECT d FROM EquipoJugador d WHERE d.equipo.idEquipo= :idEquipo")
    List<EquipoJugador> getEquipoJugadorByEquipo(@Param("idEquipo") Long idEquipo);

}
