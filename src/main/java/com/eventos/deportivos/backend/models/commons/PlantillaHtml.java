package com.eventos.deportivos.backend.models.commons;

public class PlantillaHtml {

    public static final String TEMPLATE = "<html>\n" +
            "<head>\n" +
            "    <title>Mensaje de Credenciales</title>\n" +
            "    <style>\n" +
            "        body {\n" +
            "            font-family: Arial, sans-serif;\n" +
            "            background-color: #f4f4f4;\n" +
            "            color: #333;\n" +
            "            line-height: 1.6;\n" +
            "        }\n" +
            "        .container {\n" +
            "            max-width: 600px;\n" +
            "            margin: 20px auto;\n" +
            "            padding: 20px;\n" +
            "            background: #fff;\n" +
            "            border: 1px solid #ddd;\n" +
            "        }\n" +
            "        .header {\n" +
            "            background: #0056b3;\n" +
            "            color: #fff;\n" +
            "            padding: 10px;\n" +
            "            text-align: center;\n" +
            "        }\n" +
            "        .footer {\n" +
            "            background: #0056b3;\n" +
            "            color: #fff;\n" +
            "            text-align: center;\n" +
            "            padding: 10px;\n" +
            "            font-size: 12px;\n" +
            "        }\n" +
            "        .credentials {\n" +
            "            background-color: #eef;\n" +
            "            border-left: 5px solid #5b9bd5;\n" +
            "            margin: 20px 0;\n" +
            "            padding: 10px;\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <div class=\"container\">\n" +
            "        <div class=\"header\">\n" +
            "            <h2>Bienvenido a Olimpiadas AQP</h2>\n" +
            "        </div>\n" +
            "\n" +
            "        <p>Hola {0},</p>\n" +
            "        <p>Tus credenciales de acceso son las siguientes:</p>\n" +
            "\n" +
            "        <div class=\"credentials\">\n" +
            "            <p><strong>Usuario:</strong> {1}</p>\n" +
            "            <p><strong>Contraseña:</strong> {2} </p>\n" +
            "        </div>\n" +
            "\n" +
            "        <p>Si tienes alguna pregunta o necesitas asistencia, no dudes en contactarnos.</p>\n" +
            "\n" +
            "        <div class=\"footer\">\n" +
            "            Saludos,<br>\n" +
            "            Olimpiadas AQP\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</body>\n" +
            "</html>\n";
}
