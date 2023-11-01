package com.eventos.deportivos.backend.models.controller;

import com.eventos.deportivos.backend.models.entity.Equipo;
import com.eventos.deportivos.backend.models.entity.Jugador;
import com.eventos.deportivos.backend.models.service.ServiceEntity;
import com.eventos.deportivos.backend.models.service.ServiceEntityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/equipos")
public class EquipoController {

    @Autowired
    @Qualifier("equipo")
    private ServiceEntity<Equipo, Long> equipoService;

    @Autowired
    @Qualifier("jugador")
    private ServiceEntityEntity<Jugador, Long> jugadorService;


    @GetMapping("/")
    @Secured({"ROLE_ADMIN"})
    public List<Equipo> getEquipo() {
        return equipoService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id ) {

        Equipo equipo = null;
        Map<String, Object> response = new HashMap<>();

        try {
            equipo = equipoService.porId(id);

        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (equipo== null) {
            response.put("mensaje", "El equipo ID: ".concat(id.toString().concat(" no existe en los registros.")) );
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<Equipo> (equipo, HttpStatus.OK);
    }


    @PostMapping("/")
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public ResponseEntity<?> crear(@Valid @RequestBody Equipo equipo , BindingResult result) {

        Equipo equipoNuevo= null;
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
            equipoNuevo = equipoService.guardar(equipo);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la inserccion en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Equipo creado con exito");
        response.put("equipo", equipoNuevo);
        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public ResponseEntity<?> update(@Valid @RequestBody Equipo equipo, BindingResult result, @PathVariable Long id) {

        Equipo actual = equipoService.porId(id);
        Equipo nuevo= null;
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
            response.put("mensaje", "Error: no se pudo ediar el equipo ID: ".concat(id.toString().concat(" ya que no existe en los registros.")) );
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);
        }

        try {
            actual.setNombreEquipo(equipo.getNombreEquipo());
            nuevo = equipoService.guardar(actual);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el equipo en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Equipo actualizado con exito");
        response.put("equipo", nuevo);

        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public ResponseEntity<?> delete (@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Equipo equipo = equipoService.porId(id);
            String nombreFotoAnterior = equipo.getFoto();

            if(nombreFotoAnterior !=null && nombreFotoAnterior.length()>0){
                Path rutaFotoA =  Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
                File archivoFotoA = rutaFotoA.toFile();
                if (archivoFotoA.exists() && archivoFotoA.canRead()) {
                    archivoFotoA.delete();
                }

            }
            if (equipo !=null) {
                equipoService.eliminar(id);
                response.put("mensaje", "Equipo eliminado con exito.");
                return new ResponseEntity<Map<String, Object> > (response, HttpStatus.OK);
            }

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar equipo en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "No se encontro el equipo para eliminar.");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // JUGADORES QUE TIENE UN EQUIPO
    @GetMapping("jugadores/{id}")
    public  ResponseEntity<?> jugadoresEquipo(@PathVariable Long id ) {

        List<Jugador> listaJugador = null;
        Map<String, Object> response = new HashMap<>();

        try {
            listaJugador = jugadorService.listarByEntity(id);

        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (listaJugador== null || listaJugador.isEmpty()) {
            response.put("mensaje", "No hay jugadores para el equipo.");
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(listaJugador, HttpStatus.OK);
    }
    @PostMapping("/upload")
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public ResponseEntity<?> upload(@RequestParam("archivo")MultipartFile archivo, @RequestParam("id") Long id){
        Equipo equipo ;
        Map<String, Object> response = new HashMap<>();


        equipo = equipoService.porId(id);

        if(!archivo.isEmpty()) {
            String nombreArchivo = UUID.randomUUID().toString()+"_"+ archivo.getOriginalFilename().replace(" ", "");
            Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();

            try {
                Files.copy(archivo.getInputStream(), rutaArchivo);

            }catch (Exception e){
                response.put("mensaje", "Error al subir la imagen al equipo."+ nombreArchivo );
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            String nombreFotoAnterior = equipo.getFoto();

            if(nombreFotoAnterior !=null && nombreFotoAnterior.length()>0){
                Path rutaFotoA =  Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
                File archivoFotoA = rutaFotoA.toFile();
                if (archivoFotoA.exists() && archivoFotoA.canRead()) {
                    archivoFotoA.delete();
                }
            }
            equipo.setFoto(nombreArchivo);
            equipoService.guardar(equipo);
            response.put("equipo",equipo);
            response.put("mensaje","Has subido correctamente la imagen: "+ nombreArchivo);

        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/uploads/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){

        Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
        Resource recurso= null;

        try {
            recurso= new UrlResource(rutaArchivo.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //HttpHeaders cab= new HttpHeaders();
        //cab.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recu.getFilename()  +"\"");

        if (!recurso.exists() && !recurso.isReadable()) {
            rutaArchivo = Paths.get("src/main/resources/static/images").resolve("no_equipo.png").toAbsolutePath();
            try {
                recurso= new UrlResource(rutaArchivo.toUri());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
           // throw new RuntimeException("Error no se pudo cargar la imagen: "+ nombreFoto);
        }
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename()  +"\"");
        return new ResponseEntity<Resource>(recurso, cabecera,  HttpStatus.OK);

    }



}
