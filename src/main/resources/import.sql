-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;



insert into book (id, titulo, autor, editora, anoLancamento, estaDisponivel) values (1, 'Dom Casmurro', 'Machado de Assis', 'Editora Saraiva', 1998, true);
insert into book (id, titulo, autor, editora, anoLancamento, estaDisponivel) values (2, 'Memórias Póstumas de Brás Cubas', 'Machado de Assis', 'Editora Saraiva', 1881, true);
insert into book (id, titulo, autor, editora, anoLancamento, estaDisponivel) values (3, 'O Cortiço', 'Aluísio Azevedo', 'Editora Ática', 1890, false);
insert into book (id, titulo, autor, editora, anoLancamento, estaDisponivel) values (4, 'Grande Sertão: Veredas', 'João Guimarães Rosa', 'Editora Nova Fronteira', 1956, true);
