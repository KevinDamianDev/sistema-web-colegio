package com.eventos.deportivos.backend.models.controller;

import com.eventos.deportivos.backend.models.entity.Equipo;
import com.eventos.deportivos.backend.models.entity.EquipoGrupo;
import com.eventos.deportivos.backend.models.entity.Grupo;
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
@RequestMapping("/equipogrupos")
public class EquipoGrupoController {

    @Autowired
    @Qualifier("equipogrupo")
    private ServiceEntity<EquipoGrupo, Long> equipoGrupoService;

    @Autowired
    @Qualifier("equipo")
    private ServiceEntity<Equipo, Long> equipoService;

    @Autowired
    @Qualifier("grupo")
    private ServiceEntity<Grupo,Long> grupoService;

    @Autowired
    @Qualifier("equipogrupo")
    private ServiceEntityExisteDato<EquipoGrupo, Long> equipoGrupoService2;

    @GetMapping("/")
    public List<EquipoGrupo> geEquipoGrupo() {
        return equipoGrupoService.listar();
    }

    @PostMapping("/")
    public ResponseEntity<?> crear(@RequestBody Map<String, Long> requestBody) {

        Long idEquipo= requestBody.get("idEquipo");
        Long idGrupo = requestBody.get("idGrupo");

        Grupo grupo;
        Equipo equipo;
        Map<String, Object> response = new HashMap<>();

        try {
            equipo = equipoService.porId(idEquipo);
            grupo = grupoService.porId(idGrupo);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la inserccion en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(equipo == null) {
            response.put("mensaje", "El equipo no existe en la base de datos." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        if(grupo == null) {
            response.put("mensaje", "El grupo no existe en la base de datos." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        EquipoGrupo equipoGrupo = new EquipoGrupo();
        equipoGrupo = equipoGrupoService2.existeRegistro(idEquipo, idGrupo);

        if (equipoGrupo != null) {
            response.put("mensaje", "El equipo ya esta asignado el grupo." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        EquipoGrupo equipoGrupoNuevo = new EquipoGrupo();
        equipoGrupoNuevo.setEquipo(equipo);
        equipoGrupoNuevo.setGrupo(grupo);

        EquipoGrupo nuevo = equipoGrupoService.guardar(equipoGrupoNuevo);

        response.put("mensaje", "Equipo "+ nuevo.getEquipo().getNombreEquipo() + " asignado al grupo "+ nuevo.getGrupo().getNombreGrupo());
        response.put("EquipoGrupo", nuevo);
        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);

    }


    @DeleteMapping("/")
    public ResponseEntity<?> eliminar( @RequestBody Map<String, Long> requestBody) {
        Long idEquipo= requestBody.get("idEquipo");
        Long idGrupo = requestBody.get("idGrupo");

        Grupo grupo;
        Equipo equipo;
        Map<String, Object> response = new HashMap<>();

        try {
            equipo = equipoService.porId(idEquipo);
            grupo = grupoService.porId(idGrupo);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la inserccion en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(equipo == null) {
            response.put("mensaje", "El equipo no existe en la base de datos." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        if(grupo == null) {
            response.put("mensaje", "El grupo no existe en la base de datos." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        EquipoGrupo equipoGrupo = new EquipoGrupo();
        equipoGrupo = equipoGrupoService2.existeRegistro(idEquipo, idGrupo);

        if (equipoGrupo != null) {
            equipoGrupoService.eliminar(equipoGrupo.getIdEquipoGrupo());

            response.put("mensaje", "El equipo se ha eliminado del grupo." );
            return new ResponseEntity<Map<String, Object> > (response, HttpStatus.OK);
        }
        response.put("mensaje", "No existe equipo a eliminar del grupo." );
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

    }
}
