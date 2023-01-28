create table author
(
    id     serial primary key,
    fullName varchar(128) not null
);

create table budget
(
    id     serial primary key,
    authorid int REFERENCES author(id) default null,
    year   int  not null,
    month  int  not null,
    amount int  not null,
    type   text not null

);
