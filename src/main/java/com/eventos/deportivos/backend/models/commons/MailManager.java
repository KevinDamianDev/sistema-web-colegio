package com.eventos.deportivos.backend.models.commons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.MessageFormat;

@Component
public class MailManager {

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    JavaMailSender javaMailSender;

    public void sendMessage(String email, String nombreUsuario, String usuario, String password) {
        String htmlContent = getHtmlContent(nombreUsuario, usuario, password);
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            message.setSubject("Bienvenido a Olimpiadas AQP");
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setText(htmlContent, true);
            helper.setFrom(sender);
            javaMailSender.send(message);

        }catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


    private String getHtmlContent(String nombre, String usuario, String contrasena) {
        String htmlTemplate = "<html>\n" +
                "<head>\n" +
                "    <title>Mensaje de Credenciales</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div style=\"max-width: 600px; margin: 20px auto; padding: 20px;\">\n" +
                "        <div style=\"background: #0056b3; color: #fff; text-align: center;\">\n" +
                "            <h2>Bienvenido a Olimpiadas AQP</h2>\n" +
                "        </div>\n" +
                "\n" +
                "        <p>Hola {0},</p>\n" +
                "        <p>Tus credenciales de acceso son las siguientes:</p>\n" +
                "\n" +
                "        <div>\n" +
                "            <p><strong>Usuario:</strong> {1}</p>\n" +
                "            <p><strong>Contraseña:</strong> {2}</p>\n" +
                "        </div>\n" +
                "\n" +
                "        <p>Si tienes alguna pregunta o necesitas asistencia, no dudes en contactarnos.</p>\n" +
                "\n" +
                "        <div style=\"background: #0056b3; color: #fff; text-align: center;\">\n" +
                "            Olimpiadas AQP\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";


        return MessageFormat.format(htmlTemplate, nombre, usuario, contrasena);
    }

}
