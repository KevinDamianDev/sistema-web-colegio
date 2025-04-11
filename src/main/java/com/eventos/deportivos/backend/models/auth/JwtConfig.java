package com.eventos.deportivos.backend.models.auth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class JwtConfig {

    public static String getPublicKey() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                JwtConfig.class.getClassLoader().getResourceAsStream("keys/public.pem")))) {
            return br.lines().collect(Collectors.joining("\n"));
        }
    }

    public static String getPrivateKey() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                JwtConfig.class.getClassLoader().getResourceAsStream("keys/private.pem")))) {
            return br.lines().collect(Collectors.joining("\n"));
        }
    }
}
