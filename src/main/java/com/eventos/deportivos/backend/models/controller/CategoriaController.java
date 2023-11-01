package com.eventos.deportivos.backend.models.controller;

import com.eventos.deportivos.backend.models.entity.Categoria;
import com.eventos.deportivos.backend.models.entity.Evento;
import com.eventos.deportivos.backend.models.service.ServiceEntity;
import com.eventos.deportivos.backend.models.service.ServiceUnoAMuchos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    @Qualifier("categoria")
    private ServiceEntity<Categoria, Long> categoriaService;

  /*  @Autowired
    @Qualifier("evento")
    private ServiceUnoAMuchos<Evento, Long> eventoService;*/


    @GetMapping("/")
    @Secured({"ROLE_ADMIN"})
    public List<Categoria> getCategorias() {
        return categoriaService.listar();
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> show( @PathVariable Long id ) {

        Categoria categoria = null;
        Map<String, Object> response = new HashMap<>();

        try {
            categoria = categoriaService.porId(id);
        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (categoria== null) {
            response.put("mensaje", "La categoria ID: ".concat(id.toString().concat(" no existe en los registros.")) );
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<Categoria> (categoria, HttpStatus.OK);
    }


    @PostMapping("/")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> crear(@Valid @RequestBody Categoria categoria , BindingResult result) {

        Categoria categoriaNueva= null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> error = result.getFieldErrors()
                    .stream()
                    .map(er -> "El campo '" + er.getField() + "' "+ er.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", error);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        if (categoriaService.nombreExiste(categoria.getNombreCategoria())){
            response.put("mensaje", "Nombre ya existe en la base de datos." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            categoriaNueva = categoriaService.guardar(categoria);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la inserccion en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Categoria creada con exito");
        response.put("categoria", categoriaNueva);
        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);

    }
    @PutMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> update(@Valid @RequestBody Categoria categoria, BindingResult result, @PathVariable Long id) {

        Categoria actual = categoriaService.porId(id);
        Categoria nuevo= null;
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
            response.put("mensaje", "Error: no se pudo editar la categoria ID: ".concat(id.toString().concat(" ya que no existe en los registros.")) );
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);
        }
        if (categoriaService.nombreExiste(categoria.getNombreCategoria())){
            response.put("mensaje", "Nombre ya existe en la base de datos." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            actual.setNombreCategoria(categoria.getNombreCategoria());
            nuevo = categoriaService.guardar(actual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar la categoria en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Categoria actualizada con exito");
        response.put("categoria", nuevo);

        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> delete (@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Categoria categoria =categoriaService.porId(id);

            if (categoria !=null) {
                categoriaService.eliminar(id);
                response.put("mensaje", "Categoria eliminada con exito.");
                return new ResponseEntity<Map<String, Object> > (response, HttpStatus.OK);
            }

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar categoria en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "No se encontro la categoria para eliminar.");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // EVENTOS QUE PERTENECEN A UNA CATEGORIA

   /* @GetMapping("/eventos/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> getEventosByCategoria( @PathVariable Long id ) {

        List<Evento> evento= new ArrayList<>();
        Categoria categoria;

        Map<String, Object> response = new HashMap<>();

        try {
            evento = eventoService.listarEntitysByEntity(id);
            categoria = categoriaService.porId(id);

        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (categoria == null){
            response.put("mensaje", "No existen la categoria ingresada.");
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);
        }

        if (evento== null || evento.isEmpty() ) {
            response.put("mensaje", "No existen eventos para la categoria " +categoria.getNombreCategoria());
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(evento, HttpStatus.OK);
    }*/
}
