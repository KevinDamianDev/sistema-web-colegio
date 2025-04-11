package com.eventos.deportivos.backend.models.controller.colegio;

import com.eventos.deportivos.backend.models.dao.colegio.ICursoDao;
import com.eventos.deportivos.backend.models.entity.colegio.Curso;
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
@RequestMapping("/ie/curso")
public class CursoController {

    @Autowired
    @Qualifier("curso")
    private ServiceEntityC<Curso, Long> cursoService;

    @Autowired
    private ICursoDao cursoDao;

    @GetMapping("/")
    //@Secured({"ROLE_ADMIN"})
    public List<Curso> getAll() {
        return cursoService.listar();
    }

    @GetMapping("/{id}")
    //@Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> show(@PathVariable Long id ) {

        Curso curso = null;
        Map<String, Object> response = new HashMap<>();

        try {
            curso = cursoService.porId(id);
        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (curso== null) {
            response.put("mensaje", "El registro ID: ".concat(id.toString().concat(" no existe en los registros.")) );
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<Curso> (curso, HttpStatus.OK);
    }

    @PostMapping("/")
    //@Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> crear(@Valid @RequestBody Curso curso , BindingResult result) {

        Curso cursoNuevo= null;
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
            cursoNuevo = cursoService.guardar(curso);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la inserccion en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Registro agregado.");
        response.put("curso", cursoNuevo);
        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);

    }
    @PutMapping("/{id}")
    //@Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> update(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id) {

        Curso actual = cursoService.porId(id);
        Curso nuevo= null;
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
            actual.setNombreCurso(curso.getNombreCurso());

            nuevo = cursoService.guardar(actual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el registro en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Registro actualizado con exito");
        response.put("curso", nuevo);

        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    //@Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> delete (@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Curso curso =cursoService.porId(id);

            if (curso !=null) {
                cursoService.eliminar(id);
                response.put("mensaje", "Registro eliminado con exito.");
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


    @GetMapping("/cursogrado")
    public List<Curso> getCursosPeriodoPersonaGrado(
            @RequestParam Long idPersona, @RequestParam Long idGrado,
            @RequestParam Long idPeriodo) {

        return cursoDao.findCursosByDocenteGradoPeriodo(idPersona, idGrado,idPeriodo);
    }


}
