package com.eventos.deportivos.backend.models.controller;

import com.eventos.deportivos.backend.models.entity.Duelo;
import com.eventos.deportivos.backend.models.entity.EquipoGrupo;
import com.eventos.deportivos.backend.models.entity.EquipoGrupoDuelo;
import com.eventos.deportivos.backend.models.service.ServiceEntity;
import com.eventos.deportivos.backend.models.service.ServiceEntityEntity;
import com.eventos.deportivos.backend.models.service.ServiceEntityExisteDato;
import com.eventos.deportivos.backend.models.service.ServiceUnoAMuchos;
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
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/equipogrupoduelos")
public class EquipoGrupoDueloController {

    @Autowired
    @Qualifier("equipogrupoduelo")
    private ServiceEntity<EquipoGrupoDuelo, Long> equipoGrupoDueloService;

    @Autowired
    @Qualifier("equipogrupoduelo")
    private ServiceUnoAMuchos<EquipoGrupoDuelo, Long> equipoGrupoDueloServiceUnoMuchos;

    @Autowired
    @Qualifier("equipogrupoduelo")
    private ServiceEntityEntity<EquipoGrupoDuelo, Long> equipoGrupoDueloServiceEntity;

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
    public ResponseEntity<?> crear(@RequestBody EquipoGrupoDuelo equipoGrupoDueloN) {

        Long idEquipoGrupo= equipoGrupoDueloN.getEquipoGrupo().getIdEquipoGrupo();
        Long idDuelo = equipoGrupoDueloN.getDuelo().getIdDuelo();

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
        response.put("equipoGrupoDuelo", nuevo);
        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            EquipoGrupoDuelo equipoGrupoDuelo = equipoGrupoDueloService.porId(id);
            if (equipoGrupoDuelo!=null) {
                equipoGrupoDueloService.eliminar(id);
                response.put("mensaje", "Registro eliminado con exito.");
                return new ResponseEntity<Map<String, Object> > (response, HttpStatus.OK);
            }


        }catch (DataAccessException e){
            response.put("mensaje", "Error al eliminar registro en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "No se encontro el registro para eliminar.");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }


    // EQUIPOS_GRUPOS_DUELOS QUE TIENE UNA FASE
    @GetMapping("fases/{id}")
    public  ResponseEntity<?>equiposGruposDuelosByFase(@PathVariable Long id ) {

        List<EquipoGrupoDuelo> listaEquiposGruposDuelos;
        Map<String, Object> response = new HashMap<>();

        try {
            listaEquiposGruposDuelos = equipoGrupoDueloServiceEntity.listarByEntity(id);

        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (listaEquiposGruposDuelos== null || listaEquiposGruposDuelos.isEmpty()) {
            response.put("mensaje", "No hay equipos/duelos para la fase.");
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(listaEquiposGruposDuelos, HttpStatus.OK);
    }


    // EQUIPOS_GRUPOS_DUELOS QUE TIENE UN DUELO
    @GetMapping("duelos/{id}")
    public  ResponseEntity<?>equiposGruposDuelosByDuelo(@PathVariable Long id ) {

        List<EquipoGrupoDuelo> listaEquiposGruposDuelos;
        Map<String, Object> response = new HashMap<>();

        try {
            listaEquiposGruposDuelos = equipoGrupoDueloServiceUnoMuchos.listarEntitysByEntity(id);

        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (listaEquiposGruposDuelos== null || listaEquiposGruposDuelos.isEmpty()) {
            response.put("mensaje", "No hay equipos/duelos para el duelo.");
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(listaEquiposGruposDuelos, HttpStatus.OK);
    }
}
