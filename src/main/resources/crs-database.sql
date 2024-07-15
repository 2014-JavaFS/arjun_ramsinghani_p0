create schema school;
set search_path to school;

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
	courseInitials varchar(2) not null,
	courseNumber integer not null,
	courseName varchar(100) not null,
	courseDetails varchar(255),
	spotsAvailable smallint not null,
	spotsTaken smallint not null,
	instructor varchar(40) not null
);

-- INSERT statements
insert into student values (default, 'aramsing', 'bhopal@4321', 'Arjun', 'Ramsinghani');
insert into faculty values (default, 'cjester', 'philly@8567', 'Charles', 'Jester');
insert into faculty values (default, 'kmarx', 'c0mmun!sm', 'Karl', 'Marx');
insert into course values (default, 'SWE', 8750, 'Infosys Training Full Stack', 'A course to prepare students for Infosys', 25, 24, 'Charles Jester');
insert into course values (default, 'POLS', 1011, 'An Introduction to Communism', 'An introductory course in how to create a Communist Government', 25, 25, 'Karl Marx');

-- UPDATE statments
update course set courseNumber = 2300 where instructor = 'Karl Marx';
