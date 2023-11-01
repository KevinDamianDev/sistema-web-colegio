insert into categoria(nombre_categoria) values ('Futbol 1');
insert into categoria(nombre_categoria) values ('Futbol 2');
insert into categoria(nombre_categoria) values ('Futbol 3');
insert into categoria(nombre_categoria) values ('Futbol 4');

insert into evento (nombre_evento, id_categoria) values ('Evento 1 - Olimpiadas UTP', 1);
insert into evento (nombre_evento, id_categoria) values ('Evento 2', 2);
insert into evento (nombre_evento, id_categoria) values ('Evento 3', 3);
insert into evento (nombre_evento, id_categoria) values ('Evento 4', 4);



insert into persona ( apellidos_personas, correo_persona, dni_persona, estado_persona, fecha_nac_persona,nombre_persona, password_persona,usuario_persona) values ('Damian', 'kevin@gmail.com', 77097355,1,'2000-01-06','kevin','$2a$10$2rIZlW8wNp4CyggFNE7WqehSpcmtzbVM/c8CPHzXQU6aT.JIQ/kYy','kevin123');
insert into persona ( apellidos_personas, correo_persona, dni_persona, estado_persona, fecha_nac_persona,nombre_persona, password_persona,usuario_persona) values ('Gonzales', 'samir@gmail.com', 77087559,1,'2000-01-06','Samir','$2a$10$dfnMgKf6GCAAFrn8xIJR1.dMqF7NDKGiJRzMHeibtaJ2csuKE8HQu','samir123');
insert into persona ( apellidos_personas, correo_persona, dni_persona, estado_persona, fecha_nac_persona,nombre_persona, password_persona,usuario_persona) values ('Maza', 'maza@gmail.com', 7799855,1,'2000-01-06','Carlos','$2a$10$K7cN06qfAaz4FaY0R8Y4N.FRjmzKZfNFSFq1HslsW7MsOkwlLIkUa','carlos123');
insert into persona ( apellidos_personas, correo_persona, dni_persona, estado_persona, fecha_nac_persona,nombre_persona, password_persona,usuario_persona) values ('Suarez', 'suarez@gmail.com', 77096687,1,'2000-01-06','Angel','$2a$10$K7cN06qfAaz4FaY0R8Y4N.FRjmzKZfNFSFq1HslsW7MsOkwlLIkUa','angel123');




insert into rol (nombre_roles) values ('ROLE_ADMIN');
insert into rol (nombre_roles) values ('ROLE_USER');


insert into persona_evento(id_persona,id_evento) values (3,1);
--insert into roles (nombre_roles) values ('ROLE_USER');


insert into usuarios_roles (usuario_id,role_id) values (1,1);
insert into usuarios_roles (usuario_id,role_id) values (1,2);
insert into usuarios_roles (usuario_id,role_id) values (2,2);
insert into usuarios_roles (usuario_id,role_id) values (3,2);

--insert into personas_eventos(id_persona,id_evento) values (1,1);
--insert into personas_eventos(id_persona,id_evento) values (1,2);

insert into equipo(nombre_equipo) values ('Aliaza lima');
insert into equipo(nombre_equipo) values ('U');
insert into equipo(nombre_equipo) values ('Sporting Cristal');
insert into equipo(nombre_equipo) values ('Cienciano');
insert into equipo(nombre_equipo) values ('Cienciano 2');

insert into evento_equipo(id_equipo, id_evento) values(1,1);
insert into evento_equipo(id_equipo, id_evento) values(2,1);
insert into evento_equipo(id_equipo, id_evento) values(3,1);
insert into evento_equipo(id_equipo, id_evento) values(4,1);



insert into jugador (nombre_jugador, fechanjugador,dni,dorsal_jugador) values ('Kevin Damian', '2000-01-12', 77097355,10);
insert into jugador (nombre_jugador, fechanjugador,dni,dorsal_jugador) values ('Samir Gonzales', '2000-03-14', 72297355,13);
insert into jugador (nombre_jugador, fechanjugador,dni,dorsal_jugador) values ('Edilson Gonzales', '2000-04-14', 77098355,11);
insert into jugador (nombre_jugador, fechanjugador,dni,dorsal_jugador) values ('Kenyi Gonzales', '2000-05-15', 77098765,9);
--insert into jugador (nombre_jugador, fechanjugador,dni,dorsal_jugador) values ('Paul Gonzales', '2000-11-02', 79877355,5);
--insert into jugador (nombre_jugador, fechanjugador,dni,dorsal_jugador) values ('Elvis R', '2000-11-08', 77090345,3);

insert into equipo_jugador(id_equipo, id_jugador) values (1,1);
insert into equipo_jugador(id_equipo, id_jugador) values (1,2);

insert into equipo_jugador(id_equipo, id_jugador) values (2,3);
--insert into equipo_jugador(id_equipo, id_jugador) values (2,4);


insert into fase (nombre_fase, id_evento) values ('Fase de inicio',1);
insert into fase (nombre_fase, id_evento) values ('Fase clasificatoria',1);
insert into fase (nombre_fase, id_evento) values ('Fase Final',1);

insert into fecha (nombre_fecha, id_fase) values ('Primera fecha',1);
insert into fecha (nombre_fecha, id_fase) values ('Segunda fecha',1);
insert into fecha (nombre_fecha, id_fase) values ('Tercera fecha',1);

insert into duelo(nombre_duelo, fecha_duelo, lugar_duelo, estado, id_fecha) values ('P. 1 - Alianza vs U','2023-10-02','Piura', 'Pendiente', 1);
--insert into duelo(nombre_duelo, fecha_duelo, lugar_duelo, estado, id_fecha) values ('P. 2 - SP vs Cienciano','2023-10-03','Chulucanas' ,'Pendiente',2);
--insert into duelo(nombre_duelo, fecha_duelo, lugar_duelo, estado, id_fecha) values ('P. 3 - Nuevo','2023-10-03','Chulucanas', 'Pendiente',3);


insert into grupo (nombre_grupo, id_fase) values ('Grupo A',1);
insert into grupo (nombre_grupo, id_fase) values ('Grupo B',1);

insert into equipo_grupo(id_equipo, id_grupo) values(1,1);
insert into equipo_grupo(id_equipo, id_grupo) values(2,1);
insert into equipo_grupo(id_equipo, id_grupo) values(3,2);
insert into equipo_grupo(id_equipo, id_grupo) values(4,2);

insert into equipo_estadistica(puntos, jugados, ganados, empatados,perdidos,goles_favor,goles_contra,diferencia_goles,penales,id_equipo_grupo) values(0,0,0,0,0,0,0,0,0,1);
insert into equipo_estadistica(puntos, jugados, ganados, empatados,perdidos,goles_favor,goles_contra,diferencia_goles,penales,id_equipo_grupo) values(0,0,0,0,0,0,0,0,0,2);
insert into equipo_estadistica(puntos, jugados, ganados, empatados,perdidos,goles_favor,goles_contra,diferencia_goles,penales,id_equipo_grupo) values(0,0,0,0,0,0,0,0,0,3);
insert into equipo_estadistica(puntos, jugados, ganados, empatados,perdidos,goles_favor,goles_contra,diferencia_goles,penales,id_equipo_grupo) values(0,0,0,0,0,0,0,0,0,4);




insert into equipo_grupo_duelo(id_duelo,id_equipo_grupo) values(1,1);
insert into equipo_grupo_duelo(id_duelo,id_equipo_grupo) values(1,2);
--insert into equipo_grupo_duelo(id_duelo,id_equipo_grupo) values(2,3);
--insert into equipo_grupo_duelo(id_duelo,id_equipo_grupo) values(2,4);


insert into gol (minuto_gol, id_duelo, id_equipo_jugador) values (5,1,1);

insert into gol (minuto_gol, id_duelo, id_equipo_jugador) values (10,1,2);
insert into gol (minuto_gol, id_duelo, id_equipo_jugador) values (11,1,2);

insert into gol (minuto_gol, id_duelo, id_equipo_jugador) values (15,1,3);
insert into gol (minuto_gol, id_duelo, id_equipo_jugador) values (50,1,3);
insert into gol (minuto_gol, id_duelo, id_equipo_jugador) values (25,1,3);
--insert into gol (minuto_gol, id_duelo, id_equipo_jugador) values (20,1,4);

--insert into equipo_estadistica(empatados, ganados, goles_contra, goles_favor, jugados, perdidos, puntos, id_equipo_grupo) values (0,1,0,2,1,2,3,1);
--insert into equipo_estadistica(empatados, ganados, goles_contra, goles_favor, jugados, perdidos, puntos, id_equipo_grupo) values (0,1,0,2,1,2,3,2);



