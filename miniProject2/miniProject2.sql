---테이블 생성---
create table players(
id varchar2(20) primary key,
pwd varchar2(20) not null,
name varchar2(20) not null,
signup_date date,
points number default 300
);

create table forum(
article_num number primary key,
writer varchar2(20) references players(id),
w_date date,
title varchar2(50),
content varchar2(100),
category number
);

create table rep_forum(
rep_num number primary key,
article_num number references forum(article_num),
writer varchar2(20) references players(id),
rep_date date,
content varchar2(100)
);

create table words(
num number primary key,
category varchar2(20) not null,
word varchar2(20) unique
);

---테이블 삭제---
drop table rep_forum;
drop table forum;
drop table players;
drop table words;

select * from players;
select word from words;
select * from forum;
select * from rep_forum;

---모든 시퀀스 조회---
select * from user_sequences;

---시퀀스 생성---
create sequence seq_forum;
create sequence seq_rep_forum;
create sequence seq_words;
---시퀀스 삭제---
drop sequence seq_forum;
drop sequence seq_rep_forum;
drop sequence seq_words;

select * from words;
select category, count (*) from words where category = 'animal'  group by category;

---------------수정------------------
create table players(
id varchar2(20) primary key,
pwd varchar2(20) not null,
nickname varchar2(100) not null unique,
signup_date date,
points number default 300
);

create table blacklist(
id varchar2(20) primary key
);

create table words(
num number primary key,
category varchar2(20) not null,
word varchar2(20) unique
);

create table forum(
article_num number primary key,
writer varchar2(100) references players(id) on delete cascade,
w_date date,
title varchar2(50),
content varchar2(100),
category varchar2(50)
);

create table rep_forum(
rep_num number primary key,
article_num number references forum(article_num) on delete cascade,
writer varchar2(100) references players(id) on delete cascade,
rep_date date,
content varchar2(100)
);

select * from players;
select * from words;
select * from forum;
select * from rep_forum;

create sequence seq_forum;
create sequence seq_rep_forum;
create sequence seq_words;

