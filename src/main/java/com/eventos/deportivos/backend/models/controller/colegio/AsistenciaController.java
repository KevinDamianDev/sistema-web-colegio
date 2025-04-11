package com.eventos.deportivos.backend.models.controller.colegio;

import com.eventos.deportivos.backend.models.dao.colegio.IAsistenciaDao;
import com.eventos.deportivos.backend.models.entity.colegio.Asistencia;
import com.eventos.deportivos.backend.models.entity.colegio.Evaluacion;
import com.eventos.deportivos.backend.models.service.colegio.ServiceEntityC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/ie/asistencia")
public class AsistenciaController {

    @Autowired
    @Qualifier("asistencia")
    private ServiceEntityC<Asistencia, Long> asistenciaService;

    @Autowired
    private IAsistenciaDao asistenciaDao;

    @GetMapping("/")
    //@Secured({"ROLE_ADMIN"})
    public List<Asistencia> getAll() {
        return asistenciaService.listar();
    }

    @GetMapping("/{id}")
    //@Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> show(@PathVariable Long id ) {

        Asistencia asistencia = null;
        Map<String, Object> response = new HashMap<>();

        try {
            asistencia = asistenciaService.porId(id);
        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (asistencia== null) {
            response.put("mensaje", "El registro ID: ".concat(id.toString().concat(" no existe en los registros.")) );
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<Asistencia> (asistencia, HttpStatus.OK);
    }

    @PostMapping("/")
    //@Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> crear(@Valid @RequestBody Asistencia asistencia , BindingResult result) {

        Asistencia asistenciaNuevo= null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> error = result.getFieldErrors()
                    .stream()
                    .map(er -> "El campo '" + er.getField() + "' "+ er.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", error);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            asistenciaNuevo = asistenciaService.guardar(asistencia);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la inserccion en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Asistencia agregada.");
        response.put("asistencia", asistenciaNuevo);
        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);

    }
   @PutMapping("/{id}")
    //@Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> update(@Valid @RequestBody Asistencia asistencia, BindingResult result, @PathVariable Long id) {

        Asistencia actual = asistenciaService.porId(id);
        Asistencia nuevo= null;
        Map<String, Object> response = new HashMap<>();
        //---------------MANEJO DE ERRORES--------
        if (result.hasErrors()) {
            List<String> error = result.getFieldErrors()
                    .stream()
                    .map(er -> "El campo '" + er.getField() + "' "+ er.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", error);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }


        if (actual== null) {
            response.put("mensaje", "Error: no se pudo editar el registro ID: ".concat(id.toString().concat(" ya que no existe en los registros.")) );
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);
        }

        try {
            actual.setFechaAsistencia(asistencia.getFechaAsistencia());
            actual.setEstadoAsistencia(asistencia.getEstadoAsistencia());

            nuevo = asistenciaService.guardar(actual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el registro en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Asistencia actualizada con exito");
        response.put("asistencia", nuevo);

        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    //@Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> delete (@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Asistencia asistencia =asistenciaService.porId(id);

            if (asistencia !=null) {
                asistenciaService.eliminar(id);
                response.put("mensaje", "Asistencia eliminada con exito.");
                return new ResponseEntity<Map<String, Object> > (response, HttpStatus.OK);
            }

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el registro  en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "No se encontro el registro para eliminar.");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping("/asistenciacurso")
    public List<Asistencia> getAsistenciasCursoPeriodoGrado(
            @RequestParam Long idGrado, @RequestParam Long idCurso,
            @RequestParam Long idPeriodo ) {

        return asistenciaDao.obtenerAsistenciaPorGradoCursoPeriodo(idGrado, idCurso, idPeriodo);

    }



}
