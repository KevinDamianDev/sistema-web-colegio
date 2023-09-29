insert into categorias(nombre_categoria) values ('Futbol 1');
insert into categorias(nombre_categoria) values ('Futbol 2');
insert into categorias(nombre_categoria) values ('Futbol 3');
insert into categorias(nombre_categoria) values ('Futbol 4');

insert into eventos (nombre_evento, id_categoria) values ('Evento 1', 1);
insert into eventos (nombre_evento, id_categoria) values ('Evento 2', 2);
insert into eventos (nombre_evento, id_categoria) values ('Evento 3', 3);
insert into eventos (nombre_evento, id_categoria) values ('Evento 4', 4);

insert into roles (nombre_roles) values ('ROLE_USER');
insert into roles (nombre_roles) values ('ROLE_ADMIN');

insert into personas ( apellidos_personas, coreo_persona, dni_persona, estado_persona, fecha_nac_persona,nombre_persona, password_persona,usuario_persona) values ('Damian', 'kevin@gmail.com', 77097355,1,'2000-01-06','kevin','kevin123','kevin1234');

--insert into personas_roles (id_persona,id_rol) values (1,1);
--insert into personas_roles (id_persona,id_rol) values (1,2);

--insert into personas_eventos(id_persona,id_evento) values (1,1);
--insert into personas_eventos(id_persona,id_evento) values (1,2);

insert into equipos(nombre_equipo) values ('Aliaza lima');
insert into equipos(nombre_equipo) values ('U');
insert into equipos(nombre_equipo) values ('Sporting Cristal');
insert into equipos(nombre_equipo) values ('Cienciano');

--insert into eventos_equipos(id_equipo, id_evento) values(1,1);

insert into jugadores (nombre_jugador, fechanjugador,dni,dorsal_jugador) values ('Kevin Da', '2000-01-12', 77097355,10);
insert into jugadores (nombre_jugador, fechanjugador,dni,dorsal_jugador) values ('Samir G', '2000-03-14', 72297355,13);
insert into jugadores (nombre_jugador, fechanjugador,dni,dorsal_jugador) values ('Edilson G', '2000-04-14', 77098355,11);
insert into jugadores (nombre_jugador, fechanjugador,dni,dorsal_jugador) values ('Kenyi G', '2000-05-15', 77098765,9);
insert into jugadores (nombre_jugador, fechanjugador,dni,dorsal_jugador) values ('Paul G', '2000-11-02', 79877355,5);
insert into jugadores (nombre_jugador, fechanjugador,dni,dorsal_jugador) values ('Elvis R', '2000-11-08', 77090345,3);

insert into equipos_jugadores(id_equipo, id_jugador) values (1,1);

insert into fases (nombre_fase, id_evento) values ('Fase 1',1);
insert into fases (nombre_fase, id_evento) values ('Fase 2',1);


insert into fechas (nombre_fecha, id_fase) values ('Primera fecha',1);