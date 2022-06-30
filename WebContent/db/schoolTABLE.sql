
-- 테이블, 시퀀스 삭제

-- 답변글 테이블 및 시퀀스
DROP SEQUENCE R_SEQ;
DROP TABLE REPLY;
-- 자유게시판 테이블 및 시퀀스
DROP SEQUENCE F_SEQ;
DROP TABLE FREE_BOARD;
-- 학생 테이블
DROP TABLE STUDENT;
-- 직급테이블
DROP TABLE POSITION;
-- 공지사항 게시판 시퀀스 및 테이블
DROP SEQUENCE N_SEQ;
DROP TABLE NOTICE_BOARD;
-- 선생님 테이블
DROP TABLE ADMIN;
-- 메뉴 게시판 및 시퀀스
DROP SEQUENCE M_SEQ;
DROP TABLE MENU_BOARD;


-- 테이블 생성
-- 답변글 테이블 / 시퀀스 생성 및 삭제
DROP SEQUENCE R_SEQ;
DROP TABLE REPLY;
CREATE SEQUENCE R_SEQ MAXVALUE 99999 NOCACHE NOCYCLE;
CREATE TABLE REPLY (
    rNO NUMBER(5) PRIMARY KEY,
    sID VARCHAR2(30) NOT NULL,
    rCONTENT VARCHAR2(2000) NOT NULL,
    rRDATE DATE DEFAULT SYSDATE NOT NULL,
    rIP VARCHAR2(50) NOT NULL,
    fNO NUMBER(5) NOT NULL REFERENCES FREE_BOARD
);
-- 직급 테이블
CREATE TABLE POSITION(
    pNO NUMBER(2) PRIMARY KEY,
    pNAME VARCHAR2(30) NOT NULL
);
-- 학생 테이블
CREATE TABLE STUDENT (
    sID VARCHAR2(30) PRIMARY KEY,
    sPW VARCHAR2(30) NOT NULL,
    sNAME VARCHAR2(30) NOT NULL,
    sNO NUMBER(3) UNIQUE,
    sTEL VARCHAR2(15),
    sEMAIL VARCHAR2(30),
    sGENDER VARCHAR2(10),
    sBIRTH DATE NOT NULL,
    sPHOTO VARCHAR2(100),
    pNO NUMBER(2) DEFAULT 1 REFERENCES POSITION
);

-- 관리자 테이블 
CREATE TABLE ADMIN (
    aID VARCHAR2(30) PRIMARY KEY,
    aPW VARCHAR2(30) NOT NULL,
    aNAME VARCHAR2(30) NOT NULL
);

-- 공지사항 게시판 테이블 및 시퀀스
DROP SEQUENCE N_SEQ;
DROP TABLE NOTICE_BOARD;
CREATE SEQUENCE N_SEQ MAXVALUE 99999 NOCACHE NOCYCLE;
CREATE TABLE NOTICE_BOARD (
    nNO NUMBER(5) PRIMARY KEY,
    aID VARCHAR2(30) REFERENCES ADMIN,
    nTITLE VARCHAR2(100),
    nCONTENT VARCHAR2(4000),
    nRDATE DATE DEFAULT SYSDATE NOT NULL,
    nHIT NUMBER(5) DEFAULT 0 NOT NULL
);

-- 자유게시판 시퀀스 및 테이블
DROP SEQUENCE F_SEQ;
DROP TABLE FREE_BOARD;
CREATE SEQUENCE F_SEQ MAXVALUE 99999 NOCACHE NOCYCLE;
CREATE TABLE FREE_BOARD(
    fNO NUMBER(5) PRIMARY KEY,
    sID VARCHAR2(30) NOT NULL REFERENCES STUDENT,
    fTITLE VARCHAR2(100) NOT NULL,
    fCONTENT VARCHAR2(4000),
    fFILENAME VARCHAR2(100),
    fRDATE DATE DEFAULT SYSDATE NOT NULL,
    fHIT NUMBER(5) DEFAULT 0 NOT NULL,
    fREF NUMBER(5) NOT NULL,
    fSTEP NUMBER(5) NOT NULL,
    fINDENT NUMBER(5) NOT NULL,
    fIP VARCHAR2(50) NOT NULL
);


------------------ 나중에 할 것 -------------------
-- 삭제
DROP SEQUENCE M_SEQ;
DROP TABLE MENU_BOARD;
-- 급식 게시판 및 시퀀스 / 나중에 다시 확인
CREATE SEQUENCE M_SEQ MAXVALUE 999 NOCACHE NOCYCLE;
CREATE TABLE MENU_BOARD(
    mNO NUMBER(3) PRIMARY KEY,
    mRDATE DATE DEFAULT SYSDATE NOT NULL,
    mTITLE VARCHAR2(20) NOT NULL,
    mMENU VARCHAR2(1000) NOT NULL,
    mCAL NUMBER(5) DEFAULT 0 NOT NULL,
    mPHOTO VARCHAR2(100), 
    mDATE DATE DEFAULT SYSDATE
);










