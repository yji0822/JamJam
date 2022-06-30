

-- ★ ★ 학생(STUDENT) 테이블 DAO ★ ★ -------------------------------

-- 1. 회원가입 - SID 중복체크 
SELECT * FROM STUDENT WHERE SID = 'aaa';
-- 2. 회원가입 / 아이디, 비밀번호 
INSERT INTO STUDENT (SID, SPW, SNAME, SNO, STEL, SEMAIL, SGENDER, SBIRTH, SPHOTO)
             VALUES ('FFF', '1', '유재석', 16, '010-1234-5678',  'YYY@NAVER.COM', 'M', TO_DATE('2009-08-06', 'YYYY-MM-DD'), '15.JPG');

SELECT SID, SPW, SNO, SNAME, PNAME FROM STUDENT S, POSITION P WHERE P.PNO = S.PNO ORDER BY SNO;

-- 3. 로그인 / SID, SPW 
SELECT * FROM STUDENT WHERE SID = 'aaa' AND SPW = '1';

-- 4. SID 로 DTO 가져오기 / SESSION 받아오기 
SELECT * FROM STUDENT WHERE SID = 'aaa';

-- 5. 회원정보 수정하기
UPDATE STUDENT SET SPW = '1',
                   SNAME = '강호덩',
                   STEL = '010-1111-2222',
                   SEMAIL = 'AAA@GMAIL.COM',
                   SGENDER = 'M',
                   SBIRTH = '2009-10-10',
                   SPHOTO = NULL
                WHERE SID = 'aaa';
                
SELECT * FROM STUDENT;
-- 6. 학생 리스트 출력 / 출석번호 순으로 페이징 
SELECT *
    FROM (SELECT ROWNUM RN, SID, SNAME, SNO, STEL, SEMAIL, SGENDER, SBIRTH, SPHOTO
                FROM (SELECT * FROM STUDENT ORDER BY SNO))
    WHERE RN BETWEEN 2 AND 3;

-- 7. 가입한 학생 수
SELECT COUNT(*) TOTCNT FROM STUDENT;

-- 8. 회원 탈퇴
DELETE STUDENT WHERE SID = 'aaa';

COMMIT;

-- ★ ★ FREE_BOARD 테이블 DAO ★ ★      
-- 1. 글목록 / 페이징
SELECT * FROM 
    (SELECT ROWNUM RN, A.* FROM 
    (SELECT F.*, SNAME FROM FREE_BOARD F, STUDENT S WHERE F.SID=S.SID 
                ORDER BY FREF DESC, FSTEP) A)
    WHERE RN BETWEEN 1 AND 3;

-- 서브쿼리(DAO X)
SELECT F.*, SNAME FROM FREE_BOARD F, STUDENT S WHERE F.SID=S.SID 
                ORDER BY FREF DESC, FSTEP; -- 출력 기준
                
-- 2. 글 갯수
SELECT COUNT(*) FROM FREE_BOARD;

-- 3. 글쓰기 (원글)
INSERT INTO FREE_BOARD (FNO, SID, FTITLE, FCONTENT, FFILENAME,  
        FREF, FSTEP, fINDENT, FIP)
    VALUES (F_SEQ.NEXTVAL, 'aaa','title','content', null, 
        F_SEQ.CURRVAL, 0, 0, '192.168.10.151');

SELECT * FROM FREE_BOARD;

-- 4. FHIT 증가시키기
UPDATE FREE_BOARD SET FHIT = FHIT +1 WHERE FNO = 1;

-- 5. bId로 글 dto보기
SELECT F.*, SNAME FROM FREE_BOARD F, STUDENT S WHERE F.SID = S.SID AND FNO = 1;
-- 6. 글 수정하기(FId, FTitle, FContent, FILENAME,  FIp, FDATE)
UPDATE FREE_BOARD SET FTITLE = '바뀐제목',
                    FCONTENT = '본문',
                    FFILENAME = NULL,
                    FIP = '192.168.151.10',
                    FRDATE = SYSDATE
            WHERE FNO = 1;
-- (7) 글 삭제하기(bId로 삭제하기)
COMMIT;
DELETE FROM FREE_BOARD WHERE FNO = 1;
ROLLBACK;

-- 답변글까지 모두 삭제되는 로직
DELETE FROM FREE_BOARD WHERE FREF = 1 AND (FSTEP >= 0 AND 
    FSTEP<(select NVL(MIN(FSTEP),9999) FROM FREE_BOARD WHERE FREF = 1 AND FSTEP > 0 AND FINDENT <= 0));
ROLLBACK;
--  (8) 답변글 추가전 STEP a 수행
UPDATE FREE_BOARD SET FSTEP = FSTEP+1 
    WHERE FREF = 1 AND FSTEP>0;
-- (9) 답변글 쓰기
INSERT INTO FREE_BOARD (FNO, SID, FTITLE, FCONTENT, FFILENAME,
        FREF, FSTEP, FINDENT, FIP)
    VALUES (F_SEQ.NEXTVAL, 'aaa','reply','content', null,
        1, 1, 1, '192.168.10.151');
-- (10) 회원탈퇴 하려는 사람이 쓴 글 모두 삭제 후 탈퇴
DELETE FROM FREE_BOARD WHERE SID = 'aaa';
commit;
ROLLBACK;

SELECT * FROM FREE_BOARD ORDER BY FREF DESC;


----------------------------------------------------------------------
--                          ADMIN TABLE                             --
----------------------------------------------------------------------
-- (1) admin loginCheck
SELECT * FROM ADMIN WHERE AID = 'ADMIN' AND APW = '1';
-- (2) 로그인 후 세션에 넣을 용도 : admin aid로 dto 가져오기
SELECT * FROM ADMIN WHERE AID = 'ADMIN';


----------------------------------------------------------------------
--                          REPLY TABLE                             --
----------------------------------------------------------------------

-- 1. 댓글 작성
INSERT INTO REPLY (RNO, SID, RCONTENT, RIP, FNO) 
    VALUES(R_SEQ.NEXTVAL, 'ccc', '밥', '128.1.1.1', 2);
-- 2. 댓글 출력 글번호 FNO로 댓글 가져오기 
SELECT * FROM REPLY R, FREE_BOARD F WHERE R.FNO = F.FNO ORDER BY R.RRDATE DESC;

----------------------------------------------------------------------
--                          NOTICE TABLE                            --
----------------------------------------------------------------------
-- 글번호, 관리자 아이디, 공지제목, 공지본문

-- (1) 글목록(startRow부터 endRow까지) - 글번호, 작성자이름, ...
SELECT N.*, ANAME FROM NOTICE_BOARD N, ADMIN A WHERE N.AID=A.AID 
                ORDER BY NNO DESC; -- 출력 기준 : 글번호 역순
SELECT * FROM 
    (SELECT ROWNUM RN, A.* FROM 
    (SELECT N.*, ANAME FROM NOTICE_BOARD N, ADMIN AD WHERE N.AID = AD.AID 
                ORDER BY NNO DESC) A)
    WHERE RN BETWEEN 1 AND 7; -- DAO에 들어갈 QUERY
-- (2) 글갯수
SELECT COUNT(*) FROM NOTICE_BOARD;

-- (3) 글쓰기(원글)
INSERT INTO NOTICE_BOARD (NNO, AID, NTITLE, NCONTENT)
    VALUES (N_SEQ.NEXTVAL, 'ADMIN','오늘의 공지','꼭 챙겨오기');
-- (4) FHit 하나 올리기(1번글 조회수 하나 올리기)
UPDATE NOTICE_BOARD SET NHIT = NHIT +1 WHERE NNO=1;
-- (5) bId로 글 dto보기
SELECT N.*, ANAME FROM NOTICE_BOARD N, ADMIN A WHERE N.AID = A.AID AND NNO = 1;
-- (6) 글 수정하기(FId, FTitle, FContent, FILENAME,  FIp, FDATE)
UPDATE NOTICE_BOARD SET NTITLE = '바뀐제목공지사항',
                    NCONTENT = '바뀐공지사항확인해주세요',
                    NRDATE = SYSDATE
            WHERE NNO = 1;
-- (7) 글 삭제하기(bId로 삭제하기)
COMMIT;
DELETE FROM NOTICE_BOARD WHERE NNO = 3;
ROLLBACK;

DELETE FROM FILEBOARD WHERE FGROUP = 124 AND (FSTEP>=0 AND 
    FSTEP<(select NVL(MIN(FSTEP),9999) FROM FILEBOARD WHERE FGROUP=124 AND FSTEP>0 AND FINDENT<=0));
ROLLBACK;

