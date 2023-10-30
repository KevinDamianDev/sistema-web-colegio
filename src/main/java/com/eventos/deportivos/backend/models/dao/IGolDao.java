package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.Gol;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IGolDao extends CrudRepository<Gol, Long> {
  /*  @Query("SELECT pr FROM Gol pr WHERE pr.duelo.idDuelo = :idDuelo AND pr.equipoJugador.idEquipoJugador = :idEquipoJugador")
    Gol buscarPorIdDueloYIdEquipoJugador(@Param("idDuelo") Long idDuelo, @Param("idEquipoJugador") Long idEquipoJugador);*/
    //  BUSCAR GOLES POR DUELOS
    @Query("SELECT egd FROM Gol egd WHERE egd.duelo.idDuelo = :idDuelo")
    List<Gol> findGolByIdDuelo (@Param("idDuelo") Long idDuelo);

    // BUSCAR GOLES POR EVENTO
    @Query("SELECT g FROM Gol g " +
            "JOIN g.equipoJugador ej " +
            "JOIN ej.equipo e " +
            "JOIN EventoEquipo ee ON e.idEquipo = ee.equipo.idEquipo " +
            "WHERE ee.evento.idEvento = :idEvento")
    List<Gol> findGolesByEventoId(@Param("idEvento") Long idEvento);






}
