-- ORACLE

INSERT INTO PERMISSIONS(permdesc)
select 'can:login' from dual
union all select 'create:user' from dual

union all select 'view:tickets' from dual
union all select 'view:ticket' from dual
union all select 'view:feedbacks' from dual
union all select 'make:feedback' from dual
union all select 'create:ticket' from dual
union all select 'reopen:ticket' from dual
union all select 'close:ticket' from dual
union all select 'change:priority' from dual
union all select 'delete:ticket' from dual
union all select 'open:ticket' from dual
union all select 'view:message' from dual
union all select 'create:message' from dual
union all select 'reply:message' from dual
union all select 'delete:message' from dual;


INSERT INTO ROLES(rolename)
select 'administrator' from dual
union all select 'supportassistant' from dual
union all select 'user' from dual;

INSERT INTO ROLEPERM(roleid,permid)
select 1,1 from dual
union all select 1,3 from dual
union all select 1,4 from dual
union all select 1,5 from dual
union all select 1,6 from dual
union all select 1,7 from dual
union all select 1,8 from dual
union all select 1,9 from dual
union all select 1,10 from dual
union all select 1,11 from dual
union all select 1,12 from dual
union all select 1,13 from dual
union all select 1,14 from dual
union all select 1,15 from dual;


INSERT INTO USERS(username,password,email,name,roleid)
select 'gamunu','12345','gamunuud@gmail.com','Gamunu Bandara',1 from dual;


-- MYSQL
INSERT INTO PERMISSIONS(permdesc)
VALUES('can:login'),
('create:user'),
('view:tickets'),
('view:ticket'),
('view:feedbacks'),
('make:feedback'),
('create:ticket'),
('reopen:ticket'),
('close:ticket'),
('change:priority'),
('delete:ticket'),
('open:ticket'),
('view:message'),
('create:message'),
('reply:message'),
('delete:message');


INSERT INTO ROLES(rolename)
('administrator'),
('supportassistant'),
('user');

INSERT INTO ROLEPERM(roleid,permid)
(1,1),
(1,3),
(1,4),
(1,5),
(1,6),
(1,7),
(1,8),
(1,9),
(1,10),
(1,11),
(1,12),
(1,13),
(1,14),
(1,15);


INSERT INTO USERS(username,password,email,name,roleid)
('gamunu','12345','gamunuud@gmail.com','Gamunu Bandara',1);

INSERT INTO `SUPPLIER`
(`companyname`,`phone`,`type`,`purchase`,`email`,`address`,`duration`)
VALUES
('Ewis','0112545878',null,null,'ewiscom@ewis.lk','Address ewis, Colombo',null),
('Next Computers','0118455221',null,null,'nextcom@next.lk','Address Next, Colombo',null);

INSERT INTO `BRANCH`
(`type`,`itmanager`,`address`)
VALUES
('HO','U.S Sen','Colombo 8'),
('HO','U.S Sen','Mathara'),
('HO','U.S Sen','Kandy');