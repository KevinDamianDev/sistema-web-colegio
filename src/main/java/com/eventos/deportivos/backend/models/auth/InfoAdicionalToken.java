package com.eventos.deportivos.backend.models.auth;

import com.eventos.deportivos.backend.models.entity.colegio.PersonaC;
import com.eventos.deportivos.backend.models.service.colegio.IPersonaCServiceSpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class InfoAdicionalToken implements TokenEnhancer {

    @Autowired
    private IPersonaCServiceSpring personaService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

       PersonaC persona = personaService.findByUserName(authentication.getName());
        Map<String, Object> info = new HashMap<>();
        info.put("info_adicional", "Hola que tal.".concat(authentication.getName()));
        info.put("id_usuario", persona.getIdPersona());
        info.put("nombre_usuario", persona.getNombrePersona());
        info.put("apellido_usuario", persona.getApellidosPersonas());

        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);

        return accessToken;
    }
}
