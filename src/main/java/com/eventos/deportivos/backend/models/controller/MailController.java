package com.eventos.deportivos.backend.models.controller;

import com.eventos.deportivos.backend.models.entity.Mail;
import com.eventos.deportivos.backend.models.service.AuthenticateMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    AuthenticateMail authenticateMail;


    @PostMapping("/")
    public ResponseEntity<?> send(@RequestBody Mail mail){
        Map<String, Object> response = new HashMap<>();
        try {
            authenticateMail.sendMessageUser(mail.getEmail(), mail.getNombreUsuario(), mail.getUsuario(),
                    mail.getPassword());
        }catch (Exception   e){
            response.put("error", "Error al enviar credenciales.");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Credenciales enviadas correctamente");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
}
