-- oracel 
create TABLE member(
 user_name varchar2(15) not null,
 user_id varchar2(30) PRIMARY key not NULL,
 user_pw varchar2(50) not NULL,
 user_email varchar2(20) UNIQUE not null,
 role varchar2(15) not null, -- 관리자 or 사용자 
 reward number(6,0)
);

create TABLE gamehistory(
 user_id varchar2(15) not null,
 nickname varchar2(30) not null,
 reward number(6,0),
 time_start TIMESTAMP,
 time_over TIMESTAMP);
 
--insert into member (user_name, user_id, user_pw, user_email, role, reward) values ('a', 'a1', 'a1234', 'a@gmail.com', '사용자', 1300);
--insert into member (user_name, user_id, user_pw, user_email, role, reward) values ('b', 'b1', 'b1234', 'b@gmail.com', '사용자', 100);
--insert into member (user_name, user_id, user_pw, user_email, role, reward) values ('c', 'c1', 'c1234', 'c@gmail.com', '사용자', 2000);
--insert into member (user_name, user_id, user_pw, user_email, role, reward) values ('d', 'd1', 'd1234', 'd@gmail.com', '사용자', 750);
--insert into member (user_name, user_id, user_pw, user_email, role, reward) values ('e', 'e1', 'e1234', 'e@gmail.com', '사용자', 1200);
--insert into member (user_name, user_id, user_pw, user_email, role, reward) values ('f', 'f1', 'f1234', 'f@gmail.com', '사용자', 1450);
--insert into member (user_name, user_id, user_pw, user_email, role, reward) values ('uj', 'uj302', 'uj1234', 'uj@gmail.com', '사용자', 1000); -- 게임 진행 X 사용자
--insert into member (user_name, user_id, user_pw, user_email, role, reward) values ('adminA', 'a_1', 'a1234', 'adminA@gmail.com', '관리자', 0);
--insert into member (user_name, user_id, user_pw, user_email, role, reward) values ('adminB', 'b_2', 'b1234', 'adminB@gmail.com', '관리자', 0);
--insert into member (user_name, user_id, user_pw, user_email, role, reward) values ('adminC', 'c_3', 'c1234', 'adminC@gmail.com', '관리자', 0);
 
--insert into gamehistory VALUES ('a1', 'a', 150, null, null);
--insert into gamehistory VALUES ('b1', 'b', 150, null, null);
--insert into gamehistory VALUES ('c1', 'c', 150, null, null);
--insert into gamehistory VALUES ('d1', 'd', 150, null, null);
--insert into gamehistory VALUES ('e1', 'e', 150, null, null);
--insert into gamehistory VALUES ('f1', 'f', 150, null, null);
 
--insert into gamehistory VALUES ('a1', 'a', 0, null, null);
--insert into gamehistory VALUES ('b1', 'b', 100, null, null);
--insert into gamehistory VALUES ('c1', 'c', 200, null, null);
--insert into gamehistory VALUES ('d1', 'd', 100, null, null);
--insert into gamehistory VALUES ('e1', 'e', 0, null, null);
--insert into gamehistory VALUES ('f1', 'f', 200, null, null);
 
 
 
 
 
 
 
 