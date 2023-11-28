package com.eventos.deportivos.backend.models.service;

import com.eventos.deportivos.backend.models.commons.JasperReportManager;
import com.eventos.deportivos.backend.models.commons.TipoReporteEnum;
import com.eventos.deportivos.backend.models.entity.ReporteEventosByCategoriaDTO;
import net.sf.jasperreports.engine.JRException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReporteEventosServiceImpl implements ReporteEventosByCategoriaServiceAPI{
    @Autowired
    private JasperReportManager reportManager;

    @Autowired
    private DataSource dataSource;


    @Override
    public ReporteEventosByCategoriaDTO obtenerReporteEventos(Map<String, Object> params) throws JRException, IOException, SQLException {
        String fileName = "eventos";
        ReporteEventosByCategoriaDTO dto = new ReporteEventosByCategoriaDTO();
        String extension = params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name()) ? ".xlsx"
                : ".pdf";
        dto.setFileName(fileName + extension);

        ByteArrayOutputStream stream = reportManager.export(fileName, params.get("tipo").toString(), params,
                dataSource.getConnection());

        byte[] bs = stream.toByteArray();
        dto.setStream(new ByteArrayInputStream(bs));
        dto.setLength(bs.length);

        return dto;

    }
}
