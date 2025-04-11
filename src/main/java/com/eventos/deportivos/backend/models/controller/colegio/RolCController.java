package com.eventos.deportivos.backend.models.controller.colegio;

import com.eventos.deportivos.backend.models.entity.colegio.RolC;
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
@RequestMapping("/ie/rol")
public class RolCController {

    @Autowired
    @Qualifier("rol")
    private ServiceEntityC<RolC, Long> rolService;

    @GetMapping("/")
    //@Secured({"ROLE_ADMIN"})
    public List<RolC> getAll() {
        return rolService.listar();
    }

    @GetMapping("/{id}")
    //@Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> show(@PathVariable Long id ) {

        RolC rol = null;
        Map<String, Object> response = new HashMap<>();

        try {
            rol = rolService.porId(id);
        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (rol== null) {
            response.put("mensaje", "El registro ID: ".concat(id.toString().concat(" no existe en los registros.")) );
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<RolC> (rol, HttpStatus.OK);
    }

    @PostMapping("/")
    //@Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> crear(@Valid @RequestBody RolC rol, BindingResult result) {

        RolC rolNuevo= null;
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
            rolNuevo = rolService.guardar(rol);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la inserccion en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Registro agregado.");
        response.put("rol",rolNuevo);
        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    //@Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> update(@Valid @RequestBody RolC rol, BindingResult result, @PathVariable Long id) {

        RolC actual = rolService.porId(id);
        RolC nuevo= null;
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
            actual.setNombreRoles(rol.getNombreRoles());
            nuevo = rolService.guardar(actual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el registro en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Registro actualizado con exito");
        response.put("observacion", nuevo);

        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    //@Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> delete (@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            RolC rol=rolService.porId(id);

            if (rol !=null) {
                rolService.eliminar(id);
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
}
