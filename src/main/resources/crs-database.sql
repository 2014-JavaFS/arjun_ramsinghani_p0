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

insert into faculty (faculty_id, username, password, f_name, l_name) values (default, 'cjester', 'Pass@123!', 'Charles', 'Jester');

select * from faculty where username = 'cjester' and password = 'Pass@123!';