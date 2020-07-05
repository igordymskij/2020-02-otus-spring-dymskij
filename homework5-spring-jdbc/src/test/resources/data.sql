insert into authors (authorName, lastname) values('Антуан', 'Сент-Экзюпери');
insert into jenres (jenre) values ('Приключения');
insert into jenres (jenre) values ('Фантастика');
insert into books (name, authorId, jenreId, year)
                    values ('Маленький принц', 1, 1, '1942');

insert into authors (authorId, authorName, lastname) values(null, 'Льюис', 'Керролл');
insert into books (name, authorId, jenreId, year)
                    values ('Приключения Алисы в Стране чудес', 2, 1, '1865');

insert into authors (authorName, lastname) values('Джоан', 'Роулинг');
