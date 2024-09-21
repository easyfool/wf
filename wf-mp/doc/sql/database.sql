create database if not exists mp_db default character set utf8mb4;
create user 'testuser'@'localhost' identified by '123456';
create user 'testuser'@'%' identified by '123456';
grant all on mp_db.* to 'testuser'@'localhost';
grant all on mp_db.* to 'testuser'@'%';
flush privileges;

GRANT ALL ON *.* TO 'testuser'@'localhost';
GRANT ALL ON *.* TO 'testuser'@'%';
flush privileges;
