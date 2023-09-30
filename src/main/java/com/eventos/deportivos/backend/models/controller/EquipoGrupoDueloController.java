package com.eventos.deportivos.backend.models.controller;

import com.eventos.deportivos.backend.models.entity.*;
import com.eventos.deportivos.backend.models.service.ServiceEntity;
import com.eventos.deportivos.backend.models.service.ServiceEntityExisteDato;
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
@RequestMapping("/equipogrupoduelos")
public class EquipoGrupoDueloController {

    @Autowired
    @Qualifier("equipogrupoduelo")
    private ServiceEntity<EquipoGrupoDuelo, Long> equipoGrupoDueloService;

    @Autowired
    @Qualifier("equipogrupoduelo")
    private ServiceEntityExisteDato<EquipoGrupoDuelo, Long> equipoGrupoDueloService2;

    @Autowired
    @Qualifier("equipogrupo")
    private ServiceEntity<EquipoGrupo, Long> equipoGrupoService;

    @Autowired
    @Qualifier("duelo")
    private ServiceEntity<Duelo, Long> dueloService;

    @GetMapping("/")
    public List<EquipoGrupoDuelo> geEquipoGrupoDuelo() {
        return equipoGrupoDueloService.listar();
    }

    @PostMapping("/")
    public ResponseEntity<?> crear(@RequestBody Map<String, Long> requestBody) {

        Long idEquipoGrupo= requestBody.get("idEquipoGrupo");
        Long idDuelo = requestBody.get("idDuelo");

        EquipoGrupo equipoGrupo;
        Duelo duelo;
        Map<String, Object> response = new HashMap<>();

        try {
            equipoGrupo = equipoGrupoService.porId(idEquipoGrupo);
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
        if(equipoGrupo == null) {
            response.put("mensaje", "No existen equipos en la base de datos." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        EquipoGrupoDuelo equipoGrupoDuelo= new EquipoGrupoDuelo();
        equipoGrupoDuelo =equipoGrupoDueloService2.existeRegistro(idDuelo, idEquipoGrupo);


        if (equipoGrupoDuelo != null) {
            response.put("mensaje", "El duelo ya tiene asignado el equipo." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        EquipoGrupoDuelo  equipoGrupoDueloNuevo =  new EquipoGrupoDuelo();
        equipoGrupoDueloNuevo.setEquipoGrupo(equipoGrupo);
        equipoGrupoDueloNuevo.setDuelo(duelo);
        EquipoGrupoDuelo nuevo = equipoGrupoDueloService.guardar(equipoGrupoDueloNuevo);

        response.put("mensaje", "Equipo "+ nuevo.getEquipoGrupo().getEquipo().getNombreEquipo()
                + " asignado al duelo ");
        response.put("EquipoGrupoDuelo", nuevo);
        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);

    }


    @DeleteMapping("/")
    public ResponseEntity<?> eliminar( @RequestBody Map<String, Long> requestBody) {
        Long idEquipoGrupo= requestBody.get("idEquipoGrupo");
        Long idDuelo = requestBody.get("idDuelo");

        EquipoGrupo equipoGrupo;
        Duelo duelo;
        Map<String, Object> response = new HashMap<>();

        try {
            equipoGrupo = equipoGrupoService.porId(idEquipoGrupo);
            duelo = dueloService.porId(idDuelo);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la inserccion en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(equipoGrupo == null) {
            response.put("mensaje", "El equipo no esta definido en los grupos." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        if(duelo == null) {
            response.put("mensaje", "El duelo no existe en la base de datos." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        EquipoGrupoDuelo equipoGrupoDuelo= new EquipoGrupoDuelo();
        equipoGrupoDuelo =equipoGrupoDueloService2.existeRegistro(idDuelo, idEquipoGrupo);


        if (equipoGrupoDuelo != null) {
            equipoGrupoDueloService.eliminar(equipoGrupoDuelo.getIdEquipoGrupoDuelo());

            response.put("mensaje", "El equipo se ha eliminado del duelo." );
            return new ResponseEntity<Map<String, Object> > (response, HttpStatus.OK);
        }
        response.put("mensaje", "No existe equipo a eliminar del duelo." );
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

    }
}
