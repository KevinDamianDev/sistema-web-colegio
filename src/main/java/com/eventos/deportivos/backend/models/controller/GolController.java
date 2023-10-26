package com.eventos.deportivos.backend.models.controller;

import com.eventos.deportivos.backend.models.entity.Duelo;
import com.eventos.deportivos.backend.models.entity.EquipoJugador;
import com.eventos.deportivos.backend.models.entity.Gol;
import com.eventos.deportivos.backend.models.service.ServiceEntity;
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

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/goles")
public class GolController {

    @Autowired
    @Qualifier("gol")
    private ServiceEntity<Gol, Long> golService;

   /* @Autowired
    @Qualifier("gol")
    private ServiceEntityExisteDato<Gol, Long> golServiceExisteDato;*/

   /* @Autowired
    @Qualifier("duelo")
    private ServiceEntity<Duelo, Long> dueloService;

    @Autowired
    @Qualifier("equipojugador")
    private ServiceEntity<EquipoJugador, Long> equipoJugadorService;*/


    @GetMapping("/")
    public List<Gol> geGoles() {
        return golService.listar();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id ) {

        Gol gol;
        Map<String, Object> response = new HashMap<>();

        try {
            gol = golService.porId(id);
        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (gol== null) {
            response.put("mensaje", "El gol ID: ".concat(id.toString().concat(" no existe en los registros.")) );
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<Gol> (gol, HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<?> crear(@RequestBody Gol gol) {

        Gol golNuevo;
        Map<String, Object> response = new HashMap<>();


        try {
            golNuevo = golService.guardar(gol);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la inserccion en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Gol creado con exito");
        response.put("gol", golNuevo);
        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    //@Secured({"ROLE_ADMIN","ROLE_USER"})
    public ResponseEntity<?> update(@Valid @RequestBody Gol gol, @PathVariable Long id) {


        Gol actual = golService.porId(id);
        Gol nuevo= null;
        Map<String, Object> response = new HashMap<>();

        if (actual== null) {
            response.put("mensaje", "Error: no se pudo editar el duelo ID: ".concat(id.toString().concat(" ya que no existe en los registros.")) );
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);
        }

        try {
            actual.setMinutoGol(gol.getMinutoGol());
            actual.setEquipoJugador(gol.getEquipoJugador());

            nuevo = golService.guardar(actual);


        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el registro en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Registro actualizado con exito");
        response.put("gol", nuevo);

        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Gol gol = golService.porId(id);

            if (gol !=null) {
                golService.eliminar(id);
                response.put("mensaje", "Gol eliminado con exito.");
                return new ResponseEntity<Map<String, Object> > (response, HttpStatus.OK);
            }

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar registro en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "No se encontro el gol para eliminar.");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
