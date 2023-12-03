package com.eventos.deportivos.backend.models.service;

import com.eventos.deportivos.backend.models.commons.MailManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateMail {

    @Autowired
    MailManager mailManager;

    public void sendMessageUser(String email, String nombreUsuario, String usuario, String password){
        mailManager.sendMessage( email,  nombreUsuario,  usuario,  password);
    }
}
