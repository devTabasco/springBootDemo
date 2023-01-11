-- database(table space, 테이블이 생성되는 공간)를 생성
create database db_springboot;
-- 사용자 생성
create user user_springboot@localhost identified by '1234';
-- 권한 부여
grant all privileges on db_springboot.* to user_springboot@localhost;

select *
from student_table;