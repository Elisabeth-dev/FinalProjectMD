insert into user_table(login, password, role_id)
values ('liza',
        'liza',
        (select id from role_table where name like 'ROLE_USER')
       );

insert into user_table(login, password, role_id)
values ('user',
        'user',
        (select id from role_table where name like 'ROLE_USER')
       );

insert into user_table(login, password, role_id)
values ('account',
        'account',
        (select id from role_table where name like 'ROLE_USER')
       );