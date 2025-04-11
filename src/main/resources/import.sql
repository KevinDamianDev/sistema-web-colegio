INSERT INTO persona (apellidos_personas, correo_persona, dni_persona,estado_persona,fecha_nac_persona,  nombre_persona, password_persona, telefono_persona,  usuario_persona) VALUES ('Perez Lopez', 'juan.perez@example.com', 12345678, 1, '1990-05-06', 'Juan',  '$2a$10$wZlZnsYi4Iy7ebgGM3MawuNkmA8lC8x8WJsRJmsQItln0u.Rj/1YG', 987654321, 'juanperez');
INSERT INTO persona (apellidos_personas, correo_persona, dni_persona,estado_persona,fecha_nac_persona,  nombre_persona, password_persona, telefono_persona,  usuario_persona) VALUES ('Gonzalez Ruiz', 'maria.gonzalez@example.com', 87654321, 1, '1992-07-21', 'Maria', '$2a$10$u.LY6oEb63DgLP8z1rSmY.bnh7SuyIIlK659DbNiydzi2nKLYxqna', 912345678, 'mariagonzalez');
INSERT INTO persona (apellidos_personas, correo_persona, dni_persona,estado_persona,fecha_nac_persona,  nombre_persona, password_persona, telefono_persona,  usuario_persona) VALUES ('Fernandez Diaz', 'carlos.fernandez@example.com', 45678912, 0, '1985-09-10', 'Carlos', '$2a$10$u.LY6oEb63DgLP8z1rSmY.bnh7SuyIIlK659DbNiydzi2nKLYxqna', 923456789, 'carlosf');
INSERT INTO persona (apellidos_personas, correo_persona, dni_persona,estado_persona,fecha_nac_persona,  nombre_persona, password_persona, telefono_persona,  usuario_persona) VALUES ('Rodriguez Sanchez', 'ana.rodriguez@example.com', 23456789, 1, '1998-01-30', 'Ana', '$2a$10$u.LY6oEb63DgLP8z1rSmY.bnh7SuyIIlK659DbNiydzi2nKLYxqna', 934567890, 'anarodriguez');
INSERT INTO persona (apellidos_personas, correo_persona, dni_persona,estado_persona,fecha_nac_persona,  nombre_persona, password_persona, telefono_persona,  usuario_persona) VALUES ('Martinez Castro', 'luis.martinez@example.com', 34567890, 1, '1993-11-25', 'Luis', '$2a$10$u.LY6oEb63DgLP8z1rSmY.bnh7SuyIIlK659DbNiydzi2nKLYxqna', 945678901, 'luismartinez');
INSERT INTO persona (apellidos_personas, correo_persona, dni_persona,estado_persona,fecha_nac_persona,  nombre_persona, password_persona, telefono_persona,  usuario_persona) VALUES ('Gonzales Marquez', 'mario.gonzales@example.com', 77097355, 1, '2000-11-25', 'Mario', '$2a$10$u.LY6oEb63DgLP8z1rSmY.bnh7SuyIIlK659DbNiydzi2nKLYxqna', 928238964, 'mariogonza');

INSERT INTO alumno (id_persona) values (1);
INSERT INTO alumno (id_persona) values (2);

INSERT INTO periodo (nombre_periodo, estado_periodo) values ('2024',1);
INSERT INTO periodo (nombre_periodo, estado_periodo) values ('2025',0);


INSERT INTO docente (id_persona) values (3);
INSERT INTO docente (id_persona) values (4);

INSERT INTO grado (nombre_grado, nombre_seccion) values ('Primer grado', 'A');
INSERT INTO grado (nombre_grado, nombre_seccion) values ('Primer grado', 'B');
INSERT INTO grado (nombre_grado, nombre_seccion) values ('Segundo grado', 'A');
INSERT INTO grado (nombre_grado, nombre_seccion) values ('Segundo grado', 'B');
INSERT INTO grado (nombre_grado, nombre_seccion) values ('Tercer grado', 'A');
INSERT INTO grado (nombre_grado, nombre_seccion) values ('Tercer grado', 'B');
INSERT INTO grado (nombre_grado, nombre_seccion) values ('Cuarto grado', 'A');
INSERT INTO grado (nombre_grado, nombre_seccion) values ('Cuarto grado', 'B');
INSERT INTO grado (nombre_grado, nombre_seccion) values ('Quinto grado', 'A');
INSERT INTO grado (nombre_grado, nombre_seccion) values ('Quinto grado', 'B');

INSERT INTO docente_grado (id_grado, id_docente, id_periodo) values (1, 1,1); -- DOCENTE A ENSEÑA PRIMERO A
INSERT INTO docente_grado (id_grado, id_docente, id_periodo) values (2, 1,1); -- DOCENTE A ENSEÑA PRIMERO B

--INSERT INTO docente_grado (id_grado, id_docente, id_periodo) values (1, 2,1); -- DOCENTE B ENSEÑA PRIMERO A
--INSERT INTO docente_grado (id_grado, id_docente, id_periodo) values (2, 2,1); -- DOCENTE B ENSEÑA PRIMERO B


INSERT INTO curso (nombre_curso) values ('Comunicacion');
INSERT INTO curso (nombre_curso) values ('Matematica');
INSERT INTO curso (nombre_curso) values ('Ciencias sociales');
INSERT INTO curso (nombre_curso) values ('Ingles');
INSERT INTO curso (nombre_curso) values ('Fisica');

INSERT INTO grado_curso (id_grado, id_curso) values (1, 1);
INSERT INTO grado_curso (id_grado, id_curso) values (1, 2);
INSERT INTO grado_curso (id_grado, id_curso) values (1, 3);
INSERT INTO grado_curso (id_grado, id_curso) values (1, 4);
INSERT INTO grado_curso (id_grado, id_curso) values (1, 5);

INSERT INTO grado_curso (id_grado, id_curso) values (2, 1);
INSERT INTO grado_curso (id_grado, id_curso) values (2, 2);
INSERT INTO grado_curso (id_grado, id_curso) values (2, 3);
INSERT INTO grado_curso (id_grado, id_curso) values (2, 4);
INSERT INTO grado_curso (id_grado, id_curso) values (2, 5);


INSERT INTO docente_curso (id_curso, id_docente, id_grado, id_periodo) values (1, 1,1,1);
INSERT INTO docente_curso (id_curso, id_docente, id_grado, id_periodo) values (2, 1,1,1);
INSERT INTO docente_curso (id_curso, id_docente, id_grado, id_periodo) values (3, 1,1,1);
INSERT INTO docente_curso (id_curso, id_docente, id_grado, id_periodo) values (4, 1,1,1);
INSERT INTO docente_curso (id_curso, id_docente, id_grado, id_periodo) values (5, 1,1,1);


INSERT INTO docente_curso (id_curso, id_docente, id_grado, id_periodo) values (1, 1,2,1);
INSERT INTO docente_curso (id_curso, id_docente, id_grado, id_periodo) values (2, 1,2,1);
INSERT INTO docente_curso (id_curso, id_docente, id_grado, id_periodo) values (3, 1,2,1);
INSERT INTO docente_curso (id_curso, id_docente, id_grado, id_periodo) values (4, 1,2,1);
INSERT INTO docente_curso (id_curso, id_docente, id_grado, id_periodo) values (5, 1,2,1);
--INSERT INTO docente_curso (id_curso, id_docente, id_grado, id_periodo) values (3, 1,2,1);

--INSERT INTO docente_curso (id_curso, id_docente, id_periodo) values (2, 1,1);
--INSERT INTO docente_curso (id_curso, id_docente, id_periodo) values (1, 1,1);

--INSERT INTO docente_curso (id_curso, id_docente, id_periodo) values (1, 2,2);
--INSERT INTO docente_curso (id_curso, id_docente, id_periodo) values (2, 2,2);
--INSERT INTO docente_curso (id_curso, id_docente, id_periodo) values (3, 2,2);
--INSERT INTO docente_curso (id_curso, id_docente, id_periodo) values (4, 2,2);


INSERT INTO alumno_grado (id_alumno, id_grado, id_periodo) values (1, 1,1);
INSERT INTO alumno_grado (id_alumno, id_grado, id_periodo) values (2, 1,1);


INSERT INTO alumno_curso (id_alumno, id_curso, id_periodo) VALUES (1, 1,1);
INSERT INTO alumno_curso (id_alumno, id_curso, id_periodo) VALUES (1, 2,1);
INSERT INTO alumno_curso (id_alumno, id_curso, id_periodo) VALUES (1, 3,1);
INSERT INTO alumno_curso (id_alumno, id_curso, id_periodo) VALUES (1, 4,1);
INSERT INTO alumno_curso (id_alumno, id_curso, id_periodo) VALUES (1, 5,1);

INSERT INTO alumno_curso (id_alumno, id_curso, id_periodo) VALUES (2, 1,1);
INSERT INTO alumno_curso (id_alumno, id_curso, id_periodo) VALUES (2, 2,1);
INSERT INTO alumno_curso (id_alumno, id_curso, id_periodo) VALUES (2, 3,1);
INSERT INTO alumno_curso (id_alumno, id_curso, id_periodo) VALUES (2, 4,1);
INSERT INTO alumno_curso (id_alumno, id_curso, id_periodo) VALUES (2, 5,1);



INSERT INTO evaluacion (nombre_evaluacion) values ('Examen 1');
INSERT INTO evaluacion (nombre_evaluacion) values ('Examen 2');
INSERT INTO evaluacion (nombre_evaluacion) values ('Examen 3');
INSERT INTO evaluacion (nombre_evaluacion) values ('Examen 4');
INSERT INTO evaluacion (nombre_evaluacion) values ('Examen 5');

INSERT INTO curso_evaluacion (fecha_evaluacion, id_curso, id_evaluacion) values ('2025-04-22',1, 1);
INSERT INTO curso_evaluacion (fecha_evaluacion, id_curso, id_evaluacion) values ('2025-04-23',2, 2);
INSERT INTO curso_evaluacion (fecha_evaluacion, id_curso, id_evaluacion) values ('2025-04-24',3, 3);
INSERT INTO curso_evaluacion (fecha_evaluacion, id_curso, id_evaluacion) values ('2025-04-25',4, 3);
INSERT INTO curso_evaluacion (fecha_evaluacion, id_curso, id_evaluacion) values ('2025-04-26',5, 3);

INSERT INTO asistencia (estado_asistencia, fecha_asistencia, id_alumno_curso, id_docente_curso) values ('Ausente','2025-03-03', 1,1 );
INSERT INTO asistencia (estado_asistencia, fecha_asistencia, id_alumno_curso, id_docente_curso) values ('Presente','2025-03-03', 2,1 );
INSERT INTO asistencia (estado_asistencia, fecha_asistencia, id_alumno_curso, id_docente_curso) values ('Presente','2025-03-03', 3,1 );
INSERT INTO asistencia (estado_asistencia, fecha_asistencia, id_alumno_curso, id_docente_curso) values ('Presente','2025-03-03', 4,1 );
INSERT INTO asistencia (estado_asistencia, fecha_asistencia, id_alumno_curso, id_docente_curso) values ('Presente','2025-03-03', 5,1 );


INSERT INTO horario (dia, hora_fin, hora_inicio, id_alumno_grado, id_docente_curso) values ('Lunes', '7.00 AM', '8.30 AM', 1,1 );

INSERT INTO nota (nota, id_alumno, id_curso_evaluacion) values ('A', 1, 1);
INSERT INTO nota (nota, id_alumno, id_curso_evaluacion) values ('B', 1, 2);
INSERT INTO nota (nota, id_alumno, id_curso_evaluacion) values ('A', 1, 3);
INSERT INTO nota (nota, id_alumno, id_curso_evaluacion) values ('B', 1, 4);
INSERT INTO nota (nota, id_alumno, id_curso_evaluacion) values ('C', 1, 5);

INSERT INTO observacion (fecha_observacion, observacion, id_alumno, id_docente_curso) values ('2025-03-03', 'El alumno tiene bajo rendimiento', 1, 1);

insert into rol (nombre_roles) values ('ROLE_ADMIN');
insert into rol (nombre_roles) values ('ROLE_USER');

insert into usuarios_roles (usuario_id,role_id) values (1,1);
insert into usuarios_roles (usuario_id,role_id) values (1,2);

INSERT INTO apoderado (id_persona) values (5);
INSERT INTO apoderado (id_persona) values (6);

INSERT INTO apoderado_alumno (id_alumno, id_apoderado) values (1, 1);
INSERT INTO apoderado_alumno (id_alumno, id_apoderado) values (2, 2);

INSERT INTO comunicado (descripcion, estado_comunicado, fecha_publicacion, titulo) values ('Ejemplo de comunicado 1 fffffffffffffffffffffffffffffffffffffff    ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff ffffffffffffffffffffffffffffffffffffffffff fffffffffffffffffffffffffffffffffff',1, '2025-01-01', 'Titulo 1' );
INSERT INTO comunicado (descripcion, estado_comunicado, fecha_publicacion, titulo) values ('Ejemplo de comunicado 2',1, '2025-02-02', 'Titulo 2' );
INSERT INTO comunicado (descripcion, estado_comunicado, fecha_publicacion, titulo) values ('Ejemplo de comunicado 3',0, '2025-03-03', 'Titulo 3' );
