package com.eventos.deportivos.backend.models.service;

import com.eventos.deportivos.backend.models.entity.ReporteEventosByCategoriaDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;

public interface ReporteEventosByCategoriaServiceAPI {

    ReporteEventosByCategoriaDTO
    obtenerReporteEventos(Map<String, Object> params) throws JRException, IOException, SQLException;

    ReporteEventosByCategoriaDTO
    obtenerReporteEquiposByEvento(Map<String, Object> params) throws JRException, IOException, SQLException;

    ReporteEventosByCategoriaDTO
    obtenerReporteJugadoresByCategoria(Map<String, Object> params) throws JRException, IOException, SQLException;
}
