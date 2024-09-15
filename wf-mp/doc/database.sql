create database if not exists mp_db default character set utf8mb4;
create user 'testuser'@'localhost' identified by '123456';
create user 'testuser'@'%' identified by '123456';
grant all on mp_db.* to 'testuser'@'localhost';
grant all on mp_db.* to 'testuser'@'%';
flush privileges;

GRANT ALL ON *.* TO 'testuser'@'localhost';
GRANT ALL ON *.* TO 'testuser'@'%';
flush privileges;




DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    id    BIGINT NOT NULL COMMENT '主键ID',
    name  VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age   INT NULL DEFAULT NULL COMMENT '年龄',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (id)
);

DELETE FROM `user`;

INSERT INTO `user` (id, name, age, email) VALUES
                                              (1, 'Jone', 18, 'test1@baomidou.com'),
                                              (2, 'Jack', 20, 'test2@baomidou.com'),
                                              (3, 'Tom', 28, 'test3@baomidou.com'),
                                              (4, 'Sandy', 21, 'test4@baomidou.com'),
                                              (5, 'Billie', 24, 'test5@baomidou.com');