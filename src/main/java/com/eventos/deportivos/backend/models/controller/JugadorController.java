package com.eventos.deportivos.backend.models.controller;

import com.eventos.deportivos.backend.models.entity.Equipo;
import com.eventos.deportivos.backend.models.entity.Jugador;
import com.eventos.deportivos.backend.models.service.ServiceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/jugadores")
public class JugadorController {

    @Autowired
    @Qualifier("jugador")
    private ServiceEntity<Jugador, Long> jugadorService;


    @GetMapping("/")
    public List<Jugador> getJugador() {
        return jugadorService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show( @PathVariable Long id ) {

        Jugador jugador = null;
        Map<String, Object> response = new HashMap<>();

        try {
            jugador = jugadorService.porId(id);
        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (jugador== null) {
            response.put("mensaje", "El jugador ID: ".concat(id.toString().concat(" no existe en los registros.")) );
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<Jugador> (jugador, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> crear(@Valid @RequestBody Jugador jugador , BindingResult result) {

        Jugador jugadorNuevo= null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> error = result.getFieldErrors()
                    .stream()
                    .map(er -> "El campo '" + er.getField() + "' "+ er.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", error);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
    /*    if (categoriaService.nombreExiste(categoria.getNombreCategoria())){
            response.put("mensaje", "Nombre ya existe en la base de datos." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }*/

        try {
            jugadorNuevo = jugadorService.guardar(jugador);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la inserccion en la base de datos jugador." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Jugador creado con exito");
        response.put("jugador", jugadorNuevo);
        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Jugador jugador, BindingResult result, @PathVariable Long id) {

        Jugador actualJugador = jugadorService.porId(id);

        Jugador nuevo= null;
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


        if (actualJugador== null) {
            response.put("mensaje", "Error: no se pudo editar el jugador ID: ".concat(id.toString().concat(" ya que no existe en los registros.")) );
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);
        }
       /* if (categoriaService.nombreExiste(categoria.getNombreCategoria())){
            response.put("mensaje", "Nombre ya existe en la base de datos." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }*/

        try {
            actualJugador.setNombreJugador(jugador.getNombreJugador());
            actualJugador.setFechaNJugador(jugador.getFechaNJugador());
            actualJugador.setDorsalJugador(jugador.getDorsalJugador());
            actualJugador.setDni(jugador.getDni());
            nuevo = jugadorService.guardar(actualJugador);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el jugador en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Jugador actualizado con exito");
        response.put("jugador", nuevo);

        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Jugador jugador = jugadorService.porId(id);

            if (jugador !=null) {
                jugadorService.eliminar(id);
                response.put("mensaje", "Jugador eliminado con exito.");
                return new ResponseEntity<Map<String, Object> > (response, HttpStatus.OK);
            }

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el jugador de la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "No se encontro el jugador para eliminar.");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
        Jugador jugador ;
        Map<String, Object> response = new HashMap<>();


        jugador = jugadorService.porId(id);

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
            String nombreFotoAnterior = jugador.getFoto();

            if(nombreFotoAnterior !=null && nombreFotoAnterior.length()>0){
                Path rutaFotoA =  Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
                File archivoFotoA = rutaFotoA.toFile();
                if (archivoFotoA.exists() && archivoFotoA.canRead()) {
                    archivoFotoA.delete();
                }
            }
            jugador.setFoto(nombreArchivo);
            jugadorService.guardar(jugador);
            response.put("jugador",jugador);
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
