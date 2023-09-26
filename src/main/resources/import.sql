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
