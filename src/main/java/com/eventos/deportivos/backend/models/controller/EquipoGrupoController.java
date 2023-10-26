package com.eventos.deportivos.backend.models.controller;

import com.eventos.deportivos.backend.models.entity.*;
import com.eventos.deportivos.backend.models.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/equipogrupos")
public class EquipoGrupoController {

    @Autowired
    @Qualifier("equipogrupo")
    private ServiceEntity<EquipoGrupo, Long> equipoGrupoService;

    @Autowired
    @Qualifier("equipogrupo")
    private ServiceEquipoGrupo<EquipoGrupo, Long> equipoGrupoByGrupoService;

    @Autowired
    @Qualifier("equipogrupo")
    private ServiceEntityEntity<EquipoGrupo, Long> equipoGrupoServiceService;

    @Autowired
    @Qualifier("equipo")
    private ServiceEntity<Equipo, Long> equipoService;

    @Autowired
    @Qualifier("grupo")
    private ServiceEntity<Grupo,Long> grupoService;

    @Autowired
    @Qualifier("equipogrupo")
    private ServiceEntityExisteDato<EquipoGrupo, Long> equipoGrupoService2;

    @Autowired
    @Qualifier("equipoestadistica")
    private ServiceEntity<EquipoEstadistica, Long> equipoEstadisticaService;

    @GetMapping("/")
    public List<EquipoGrupo> geEquipoGrupo() {
        return equipoGrupoService.listar();
    }

    @PostMapping("/")
    public ResponseEntity<?> crear(@RequestBody EquipoGrupo equipoGrupoN) {

        Long idEquipo= equipoGrupoN.getEquipo().getIdEquipo();
        Long idGrupo = equipoGrupoN.getGrupo().getIdGrupo();

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
        response.put("equipoGrupo", nuevo);

        // CREA ESTADISTICAS CADA VEZ QUE CREA UN EQUIPO_GRUPO
        EquipoEstadistica equipoEstadistica = new EquipoEstadistica();

        try {
            equipoEstadistica.setEquipoGrupo(nuevo);
            equipoEstadistica.setEmpatados(0);
            equipoEstadistica.setGanados(0);
            equipoEstadistica.setGolesContra(0);
            equipoEstadistica.setGolesFavor(0);
            equipoEstadistica.setJugados(0);
            equipoEstadistica.setPerdidos(0);
            equipoEstadistica.setPuntos(0);
            equipoEstadistica.setDiferenciaGoles(0);
            equipoEstadistica.setPenales(0);

            equipoEstadisticaService.guardar(equipoEstadistica);

        }catch (DataAccessException e) {
            response.put("mensaje", "Error al ingresar las estadisticas en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);

    }

    /*@Autowired
    @Qualifier("equipoestadistica")
    private ServiceUnoAMuchos<EquipoEstadistica, Long> equipoEstadisticaServiceUnoMuchos;

    @Autowired
    @Qualifier("equipoestadistica")
    private ServiceEntity<EquipoEstadistica, Long> equipoEstadisticaServiceDelete;*/

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
           // List<EquipoEstadistica>  equipoEstadistica = equipoEstadisticaServiceUnoMuchos.listarEntitysByEntity(id);

            EquipoGrupo equipoGrupoNuevo = equipoGrupoService.porId(id);

           if (equipoGrupoNuevo!=null) {
                // ELIMINA SUS ESTADISTICAS
/*
                for (EquipoEstadistica estadistica : equipoEstadistica) {
                    System.out.println("Entrando a eliminar equipo, idEquipoGrupo: " + id + " :: " + estadistica.getEquipoGrupo().getIdEquipoGrupo());
                    if (estadistica.getEquipoGrupo().getIdEquipoGrupo().equals(id)) {
                        System.out.println("id: " + id + ", estadistica.getIdEquipoEstadistica(): " + estadistica.getIdEquipoEstadistica());
                        equipoEstadisticaServiceDelete.eliminar(estadistica.getIdEquipoEstadistica());
                    }
                }
*/

               // equipoEstadisticaServiceDelete.eliminar(equipoEstadistica.getIdEquipoEstadistica());

                // ELIMINA EQUIPO_GRUPO
                equipoGrupoService.eliminar(id);
                response.put("mensaje", "Registro eliminado con exito.");
                return new ResponseEntity<Map<String, Object> > (response, HttpStatus.OK);
            }


        }catch (DataAccessException e){
            response.put("mensaje", "Error al eliminar registro en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "No se encontro el registro para eliminar.");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

    }


    // EQUIPOS_GRUPOS QUE TIENE UNA FASE
    @GetMapping("fases/{id}")
    public  ResponseEntity<?>equipoGrupoByFase(@PathVariable Long id ) {

        List<EquipoGrupo> listaEquiposGrupos;
        Map<String, Object> response = new HashMap<>();

        try {
            listaEquiposGrupos = equipoGrupoServiceService.listarByEntity(id);

        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (listaEquiposGrupos== null || listaEquiposGrupos.isEmpty()) {
            response.put("mensaje", "No hay equipos/grupos para la fase.");
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(listaEquiposGrupos, HttpStatus.OK);
    }


    // EQUIPOS_GRUPOS QUE TIENE UN GRUPO
    @GetMapping("grupos/{id}")
    public  ResponseEntity<?>equipoGrupoByGrupo(@PathVariable Long id ) {

        List<EquipoGrupo> listaEquiposGrupos;
        Map<String, Object> response = new HashMap<>();

        try {
            listaEquiposGrupos = equipoGrupoByGrupoService.listarEquipoByGrupo(id);

        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (listaEquiposGrupos== null || listaEquiposGrupos.isEmpty()) {
            response.put("mensaje", "No hay equipos/grupos para el grupo.");
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(listaEquiposGrupos, HttpStatus.OK);
    }

}
