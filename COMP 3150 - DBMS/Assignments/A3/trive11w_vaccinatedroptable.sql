delete from VACCINATES;
delete from ADMINISTERS;
delete from SELLS;
delete from VACCINE;
delete from PCOMPANY;
delete from PERSON;
delete from CENTRE;

commit;

drop table VACCINATES cascade constraints;
drop table ADMINISTERS cascade constraints;
drop table SELLS cascade constraints;
drop table VACCINE cascade constraints;
drop table PCOMPANY cascade constraints;
drop table PERSON cascade constraints;
drop table CENTRE cascade constraints;

commit;

