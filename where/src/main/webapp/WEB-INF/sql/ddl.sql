---------------------------------------------
-- oracle 계정
create user Board identified by Board
grant connect, resources to Board;
---------------------------------------------

-----------------------------------------------
-- 회원관리
-- 시퀀스 생성
CREATE SEQUENCE mid_seq
START WITH 1 INCREMENT BY 1  NOMAXVALUE  NOCYCLE;

-- 테이블생성
CREATE TABLE member (
    mid NUMBER PRIMARY KEY,
    email VARCHAR2(100)  NULL UNIQUE,
    pw VARCHAR2(100)  NULL,
    niname VARCHAR2(100) null,
    name VARCHAR2(100)  NULL,
    regdate TIMESTAMP DEFAULT SYSTIMESTAMP,
    moddate TIMESTAMP DEFAULT SYSTIMESTAMP,
    locagree CHAR(1),
    sessionid VARCHAR2(100),
    limittime VARCHAR2(100),
    authority VARCHAR2(50) DEFAULT 'USER'
);

-- 트리거 생성
CREATE OR REPLACE TRIGGER mid_trigger
BEFORE INSERT ON member
FOR EACH ROW
BEGIN
    SELECT mid_seq.NEXTVAL INTO :new.mid FROM dual;
END;
/
INSERT INTO member (email, pw,niname, name, regdate, moddate, locagree)
VALUES ('tkd@example.com', 'password123','조커' ,'John Doe', SYSTIMESTAMP, SYSTIMESTAMP, 'Y');
SELECT *from member;
---------------------------------------------------------


---------------------------------------------------------
-- 게시판
CREATE table board (
 bid number PRIMARY KEY,
 mid NUMBER(20) ,
 niname varchar2(100) ,
 title varchar2(500) ,
 btype CHAR(1) ,
 cont VARCHAR2(4000),
 likes number(20) default 0,
 counts number(10) default 0,
 replycnt number(10),
 regdate date 
);

CREATE table attach (
 fullname varchar2(150), 
 bid NUMBER(20),
 aid NUMBER(20),
 regdate date
);

 CREATE table reply (
 rid number  primary KEY,
 bid number(20),
 replytext varchar2(1000),
 replyer varchar2(20),
 updateregdate date,
 regdate date
);
CREATE SEQUENCE reply_rid_SEQ
       INCREMENT BY 1
       START WITH 1
       MINVALUE 1
       MAXVALUE 99999999999
       NOCYCLE
       NOCACHE
       NOORDER;

 CREATE SEQUENCE board_bid_SEQ
       INCREMENT BY 1
       START WITH 1
       MINVALUE 1
       MAXVALUE 99999999999
       NOCYCLE
       NOCACHE
       NOORDER;
 CREATE SEQUENCE board_bid_SEQ
   START WITH 1
   INCREMENT BY 1
   NOCACHE
   NOCYCLE;
   
     CREATE SEQUENCE attach_aid_SEQ
       INCREMENT BY 1
       START WITH 1
       MINVALUE 1
       MAXVALUE 99999999999
       NOCYCLE
       NOCACHE
       NOORDER;


insert into board 
values(Board_bid_seq.nextval, 3,  '유재석', '제목', 'N', 'Y', 1, 1, 0, SYSDATE);

ALTER TABLE attach
ADD CONSTRAINT fk_bid
FOREIGN KEY (bid)
REFERENCES board(bid)
ON DELETE CASCADE;
---------------------------------------------------------------------------