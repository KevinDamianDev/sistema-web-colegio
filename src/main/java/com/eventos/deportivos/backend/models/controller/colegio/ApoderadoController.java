package com.eventos.deportivos.backend.models.controller.colegio;

import com.eventos.deportivos.backend.models.entity.colegio.Apoderado;
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
@RequestMapping("/ie/apoderado")
public class ApoderadoController {

    @Autowired
    @Qualifier("apoderado")
    private ServiceEntityC<Apoderado, Long> apoderadoService;

    @GetMapping("/")
    //@Secured({"ROLE_ADMIN"})
    public List<Apoderado> getAll() {
        return apoderadoService.listar();
    }

    @GetMapping("/{id}")
    //@Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> show(@PathVariable Long id ) {

        Apoderado apoderado = null;
        Map<String, Object> response = new HashMap<>();

        try {
            apoderado = apoderadoService.porId(id);
        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (apoderado== null) {
            response.put("mensaje", "El apoderado ID: ".concat(id.toString().concat(" no existe en los registros.")) );
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<Apoderado> (apoderado, HttpStatus.OK);
    }

    @PostMapping("/")
    //@Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> crear(@Valid @RequestBody Apoderado apoderado , BindingResult result) {

        Apoderado apoderadoNuevo= null;
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
            apoderadoNuevo = apoderadoService.guardar(apoderado);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la inserccion en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Apoderado creado con exito.");
        response.put("apoderado", apoderadoNuevo);
        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    //@Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> delete (@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Apoderado apoderado =apoderadoService.porId(id);

            if (apoderado !=null) {
                apoderadoService.eliminar(id);
                response.put("mensaje", "Apoderado eliminado.");
                return new ResponseEntity<Map<String, Object> > (response, HttpStatus.OK);
            }

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el apoderado." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "No se encontro el registro para eliminar.");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
