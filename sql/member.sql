-- oracel 
create TABLE member(
 user_name varchar2(15) not null,
 user_id varchar2(30) PRIMARY key not NULL,
 user_pw varchar2(50) not NULL,
 user_email varchar2(20) UNIQUE not null,
 role varchar2(15) not null, -- 관리자 or 사용자 
 reward number(6,0)
);

