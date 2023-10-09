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
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goles")
public class GolController {

    @Autowired
    @Qualifier("gol")
    private ServiceEntity<Gol, Long> golService;

   /* @Autowired
    @Qualifier("gol")
    private ServiceEntityExisteDato<Gol, Long> golServiceExisteDato;*/

    @Autowired
    @Qualifier("duelo")
    private ServiceEntity<Duelo, Long> dueloService;

    @Autowired
    @Qualifier("equipojugador")
    private ServiceEntity<EquipoJugador, Long> equipoJugadorService;


    @GetMapping("/")
    public List<Gol> geGoles() {
        return golService.listar();
    }

    @PostMapping("/")
    public ResponseEntity<?> crear(@RequestBody Map<String, Long> requestBody) {

        Long idEquipoJugador= requestBody.get("idEquipoJugador");
        Long idDuelo = requestBody.get("idDuelo");
        Long gol = requestBody.get("gol");

        EquipoJugador equipoJugador;
        Duelo duelo;
        Map<String, Object> response = new HashMap<>();

        try {
            equipoJugador = equipoJugadorService.porId(idEquipoJugador);
            duelo = dueloService.porId(idDuelo);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la inserccion en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(duelo == null) {
            response.put("mensaje", "El duelo no existe en la base de datos." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        if(equipoJugador == null) {
            response.put("mensaje", "No existen equipos en la base de datos." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

     /*   Gol g = new Gol();
        g = golServiceExisteDato.existeRegistro(idDuelo,idEquipoJugador );

        if (g != null) {
            response.put("mensaje", " ya tiene asignado el equipo." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }*/

        Gol golNuevo = new Gol();
        golNuevo.setDuelo(duelo);
        golNuevo.setEquipoJugador(equipoJugador);
        golNuevo.setMinutoGol(gol);
        Gol nuevo = golService.guardar(golNuevo);

        response.put("mensaje", "Gol en el minuto "+ nuevo.getMinutoGol()
                + " asignado al jugador "+ equipoJugador.getJugador().getNombreJugador() + " del equipo "+ equipoJugador
                .getEquipo().getNombreEquipo());
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
