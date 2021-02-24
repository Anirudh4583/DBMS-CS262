-- create database
create database employee;
-- using the created database
use employee;
-- create table
create table employee (
	SNo int, Name varchar(255), 
	Designation varchar(255), 
	Branch varchar(255), 
	R201951024 int
);
select * from employee;		-- show table

-- insert values into the table
insert into employee value (
	1, 
	"Ram", 
	"Manager", 
	"Chennai", 
	NULL
);
insert into employee value (
	2, 
	"Santosh", 
	"Superviser", 
	"Madurai", 
	NULL
);
insert into employee value (
	3, 
	"Hari", 
	"Assistant", 
	"Trichy", 
	NULL
);

-- add salary column
alter table employee add(Salary int);

-- alter table column name
alter table employee rename column name to employeeName;

-- copy table employee into table emp
create table emp as select * from employee;

select * from emp;

-- truncate table
truncate table emp;

-- delete row from table employee
delete from employee where SNo = 2;

-- drop the table
drop table employee;
