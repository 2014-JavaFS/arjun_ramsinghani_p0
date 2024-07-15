create schema school;
set search_path to school;

-- DROP statements
drop table student;
drop table faculty;
drop table course;

-- CREATE statements
create table student(
	student_id serial primary key,
	username varchar(10) not null,
	password varchar(20) not null,
	f_name varchar(20) not null,
	l_name varchar(20) not null
);

create table faculty(
	faculty_id serial primary key,
	username varchar(10) not null,
	password varchar(20) not null,
	f_name varchar(20) not null,
	l_name varchar(40) not null
);

create table course(
	course_id serial primary key,
	courseInitials varchar(4) not null,
	courseNumber integer not null,
	courseName varchar(100) not null,
	courseDetails varchar(255),
	spotsAvailable smallint not null,
	spotsTotal smallint not null,
	instructor varchar(40) not null
);

create table course_student(
    registration_id serial primary key,
    course_id integer not null,
    student_id integer not null,

    foreign key (course_id) references course(course_id) on delete cascade,
    foreign key (student_id) references student(student_id) on delete cascade
);

-- INSERT statements
insert into student values (default, 'aramsing', 'bhopal@4321', 'Arjun', 'Ramsinghani');
insert into faculty values (default, 'cjester', 'philly@8567', 'Charles', 'Jester');
insert into faculty values (default, 'kmarx', 'c0mmun!sm', 'Karl', 'Marx');
insert into course values (default, 'SWE', 8750, 'Infosys Training Full Stack', 'A course to prepare students for Infosys', 25, 24, 'Charles Jester');
insert into course values (default, 'POLS', 2300, 'An Introduction to Communism', 'An introductory course in how to create a Communist Government', 25, 25, 'Karl Marx');

insert into student values (default, 'kayshova', 'toledo@8765', 'Kayshov', 'Agnihotri');

-- UPDATE statments
update course set spotsAvailable = 300 where instructor = 'Karl Marx';

-- SELECT statements
select * from faculty where username = 'kmarx' AND password = 'c0mmun!sm';
select * from faculty;
select * from student;
select * from course;
select * from student where username = 'kayshova' AND password = 'toledo@8765';

-- DELETE statements
delete from student where student_id = 2;
