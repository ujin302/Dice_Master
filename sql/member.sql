-- oracel 
create TABLE member(
 user_name varchar2(15) not null,
 user_id varchar2(30) PRIMARY key not NULL,
 user_pw varchar2(50) not NULL,
 user_email varchar2(20) UNIQUE not null,
 role varchar2(15) not null, -- 관리자 or 사용자 
 reward number(6,0)
);

create Tabel gamehistory(
 user_id varchar2(15) not null,
 nickname varchar2(30) not null,
 reward number(6,0),
 time_start TIMESTAMP,
 time_over TIMESTAMP);
 
--insert into gamehistory VALUES ('a1', 'a', 150, null, null);
--insert into gamehistory VALUES ('b1', 'b', 150, null, null);
--insert into gamehistory VALUES ('c1', 'c', 150, null, null);
--insert into gamehistory VALUES ('d1', 'd', 150, null, null);
--insert into gamehistory VALUES ('e1', 'e', 150, null, null);
--insert into gamehistory VALUES ('f1', 'f', 150, null, null);

 
 