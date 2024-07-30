-- oracel 
create TABLE member(
 user_name varchar2(15) not null,
 uesr_id varchar2(30) PRIMARY key not NULL,
 uesr_pw varchar2(50) not NULL,
 uesr_email varchar2(20) UNIQUE not null,
 role varchar2(15) not null -- 관리자 or 사용자 
);

