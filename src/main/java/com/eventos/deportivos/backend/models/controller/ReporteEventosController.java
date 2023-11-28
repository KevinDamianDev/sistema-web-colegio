package com.eventos.deportivos.backend.models.controller;

import java.io.ByteArrayInputStream;
import java.util.Map;

import com.eventos.deportivos.backend.models.commons.TipoReporteEnum;
import com.eventos.deportivos.backend.models.entity.ReporteEventosByCategoriaDTO;
import com.eventos.deportivos.backend.models.service.ReporteEventosByCategoriaServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/report")
public class ReporteEventosController {
    @Autowired
    private ReporteEventosByCategoriaServiceAPI reporteEventoServiceAPI;
    @GetMapping(path = "/eventos/download")
    public ResponseEntity<Resource> download(@RequestParam Map<String, Object> params) {
        try {
            String tipoReporte = params.get("tipo").toString();
            MediaType mediaType;
            if (TipoReporteEnum.EXCEL.name().equalsIgnoreCase(tipoReporte)) {
                mediaType = MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            } else if (TipoReporteEnum.PDF.name().equalsIgnoreCase(tipoReporte)) {
                mediaType = MediaType.APPLICATION_PDF;
            } else {
                return ResponseEntity.badRequest().build(); // Tipo de reporte no soportado
            }

            // Asegúrate de que el parámetro categoria es un entero válido
            if (params.get("categoria") != null) {
                Integer categoria = Integer.parseInt(params.get("categoria").toString());
                params.put("categoria", categoria);
            }

            // Continuar con la generación del reporte
            ReporteEventosByCategoriaDTO dto = reporteEventoServiceAPI.obtenerReporteEventos(params);
            InputStreamResource streamResource = new InputStreamResource(dto.getStream());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dto.getFileName() + "\"")
                    .contentLength(dto.getLength())
                    .contentType(mediaType)
                    .body(streamResource);
        } catch (NumberFormatException e) {
            // Manejo de parámetro categoria inválido
            return ResponseEntity.badRequest().body(new InputStreamResource(new ByteArrayInputStream(("Error en el formato del parámetro 'categoria': debe ser un número entero.").getBytes())));
        } catch (Exception e) {
            // Manejo de otros errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new InputStreamResource(new ByteArrayInputStream(("Error interno del servidor").getBytes())));
        }
    }


}
