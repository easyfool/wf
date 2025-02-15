create database if not exists hmdp_db default character set utf8mb4;
create user 'hmdp'@'localhost' identified by '123456';
create user 'hmdp'@'%' identified by '123456';
grant all on hmdp_db.* to 'hmdp'@'localhost';
grant all on hmdp_db.* to 'hmdp'@'%';
flush privileges;

GRANT ALL ON *.* TO 'hmdp'@'localhost';
GRANT ALL ON *.* TO 'hmdp'@'%';
flush privileges;
