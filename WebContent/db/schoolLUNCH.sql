DROP TABLE LUNCH;
CREATE TABLE LUNCH(
    LNO      NUMBER(3)    PRIMARY KEY,
    LDATE    DATE         NOT NULL,
    AMPM     VARCHAR2(10) NOT NULL,
    MENU     VARCHAR2(1000) NOT NULL,
    CALORIE  NUMBER(5,1)   NOT NULL,
    PHOTO    VARCHAR2(1000),
    DAY      NUMBER(2)    NOT NULL
);
DROP SEQUENCE LUNCH_SEQ;
CREATE SEQUENCE LUNCH_SEQ MAXVALUE 9999 NOCACHE NOCYCLE;
-- 0. 더미데이터
INSERT INTO LUNCH (LNO, LDATE, AMPM, MENU, CALORIE, PHOTO, day) 
    VALUES (LUNCH_SEQ.NEXTVAL, '2022-06-27','중식','찹쌀밥,미역국,돼지갈비찜,견과류멸치볶음',862.9,NULL,
    to_char(to_date('2022-06-27'),'dd'));
    INSERT INTO LUNCH (LNO, LDATE, AMPM, MENU, CALORIE, PHOTO, day) 
    VALUES (LUNCH_SEQ.NEXTVAL, '2022-06-27','석식','보리밥,미역국,돼지갈비찜,견과류멸치볶음',862.9,NULL,
    to_char(to_date('2022-06-27'),'dd'));
INSERT INTO LUNCH (LNO, LDATE, AMPM, MENU, CALORIE, PHOTO, day) 
    VALUES (LUNCH_SEQ.NEXTVAL, '2022-06-01','중식','찹쌀밥,미역국,돼지갈비찜,견과류멸치볶음',862.9,NULL,
    to_char(to_date('2022-06-01'),'dd'));
INSERT INTO LUNCH (LNO, LDATE, AMPM, MENU, CALORIE, PHOTO, day) 
    VALUES (LUNCH_SEQ.NEXTVAL, '2022-07-01','중식','보리밥,미역국,돼지갈비찜,견과류멸치볶음',862.9,NULL,
    to_char(to_date('2022-07-01'),'dd'));
INSERT INTO LUNCH (LNO, LDATE, AMPM, MENU, CALORIE, PHOTO, day) 
    VALUES (LUNCH_SEQ.NEXTVAL, '2022-07-04','중식','흑미밥,미역국,돼지갈비찜,견과류멸치볶음',862.9,NULL,
    to_char(to_date('2022-07-01'),'dd'));
INSERT INTO LUNCH (LNO, LDATE, AMPM, MENU, CALORIE, PHOTO, day) 
    VALUES (LUNCH_SEQ.NEXTVAL, '2022-07-04','석식','저녁밥,미역국,돼지갈비찜,견과류멸치볶음',862.9,'7.jfif',
    to_char(to_date('2022-07-04'),'dd'));
SELECT * FROM LUNCH ORDER BY LDATE, AMPM DESC;


-- 7월 식단 더미데이터
INSERT INTO LUNCH (LNO, LDATE, AMPM, MENU, CALORIE, PHOTO, day) 
    VALUES (LUNCH_SEQ.NEXTVAL, '2022-07-01','중식','귀리밥, 동태매운탕, 닭봉데리소스구이, 요맘때', 862.9, '0701.jpg',
    to_char(to_date('2022-07-01'),'dd'));
INSERT INTO LUNCH (LNO, LDATE, AMPM, MENU, CALORIE, PHOTO, day) 
    VALUES (LUNCH_SEQ.NEXTVAL, '2022-07-04','중식','야채비빔밥, 근대된장국, 까르보나라감자그라탕, 총각김치', 929.9, '0704.jpg',
    to_char(to_date('2022-07-04'),'dd'));
INSERT INTO LUNCH (LNO, LDATE, AMPM, MENU, CALORIE, PHOTO, day) 
    VALUES (LUNCH_SEQ.NEXTVAL, '2022-07-05','중식', '혼합잡곡밥, 돈육김치찌개, 콘치즈바싹불고기, 배추김치', 1200.1, '0705.jpg',
    to_char(to_date('2022-07-05'),'dd'));
INSERT INTO LUNCH (LNO, LDATE, AMPM, MENU, CALORIE, PHOTO, day) 
    VALUES (LUNCH_SEQ.NEXTVAL, '2022-07-06','중식','작은밥, 오야양파무침, 고구마치즈롤까스, 석박지, 피크닉', 999.9, '0706.jpg',
    to_char(to_date('2022-07-06'),'dd'));
INSERT INTO LUNCH (LNO, LDATE, AMPM, MENU, CALORIE, PHOTO, day) 
    VALUES (LUNCH_SEQ.NEXTVAL, '2022-07-07','중식','치밥, 홍대새우고로케, 배추김치, 수박화채, 치킨무', 1200.9, '0707.jpg',
    to_char(to_date('2022-07-07'),'dd'));
INSERT INTO LUNCH (LNO, LDATE, AMPM, MENU, CALORIE, PHOTO, day) 
    VALUES (LUNCH_SEQ.NEXTVAL, '2022-07-08','중식', '작은밥, 냉쫄면, 통살닭꼬치까스, 계란토스트', 989.9, '0708.jpg',
    to_char(to_date('2022-07-08'),'dd'));
commit;


INSERT INTO LUNCH (LNO, LDATE, AMPM, MENU, CALORIE, PHOTO, day) 
    VALUES (LUNCH_SEQ.NEXTVAL, '2022-07-01','석식','스팸깍두기볶음밥, 시크릿후라이드꼬치, 피크닉', 787.8, '07011.jpg',
    to_char(to_date('2022-07-01'),'dd'));
INSERT INTO LUNCH (LNO, LDATE, AMPM, MENU, CALORIE, PHOTO, day) 
    VALUES (LUNCH_SEQ.NEXTVAL, '2022-07-04','석식','귀리밥, 안동찜닭, 오이양파무침, 배추김치', 822.8, '07041.jpg',
    to_char(to_date('2022-07-04'),'dd'));
INSERT INTO LUNCH (LNO, LDATE, AMPM, MENU, CALORIE, PHOTO, day) 
    VALUES (LUNCH_SEQ.NEXTVAL, '2022-07-05','석식','잡곡밥, 참치김치찌개, 애호박전, 미숫가루', 787.8, '07051.jpg',
    to_char(to_date('2022-07-05'),'dd'));
INSERT INTO LUNCH (LNO, LDATE, AMPM, MENU, CALORIE, PHOTO, day) 
    VALUES (LUNCH_SEQ.NEXTVAL, '2022-07-06', '석식', '잡곡밥, 무채다시마국, 청경채무침, 핫도그', 980.8, '07061.jpg',
    to_char(to_date('2022-07-06'),'dd'));
INSERT INTO LUNCH (LNO, LDATE, AMPM, MENU, CALORIE, PHOTO, day) 
    VALUES (LUNCH_SEQ.NEXTVAL, '2022-07-07','석식','현미밥, 오징어무국, 취나물무침, 배추김치', 787.8, '07071.jpg',
    to_char(to_date('2022-07-07'),'dd'));
INSERT INTO LUNCH (LNO, LDATE, AMPM, MENU, CALORIE, PHOTO, day) 
    VALUES (LUNCH_SEQ.NEXTVAL, '2022-07-08','석식','현미밥, 미니스테이크, 계란찜, 배추김치', 676.8, '07081.jpg',
    to_char(to_date('2022-07-08'),'dd'));

-- 1. 메뉴 등록
INSERT INTO LUNCH (LNO, LDATE, AMPM, MENU, CALORIE, PHOTO, day) 
    VALUES (LUNCH_SEQ.NEXTVAL, '2022-06-28','중식','밥,콩나물국,돼지갈비찜,견과류멸치볶음',860,'7.jfif',
    to_char(to_date('2022-06-28'),'dd'));
-- 2. 출력
SELECT * FROM LUNCH WHERE TO_CHAR(LDATE, 'YYYY-MM') = '2022'||'-'||'06' ORDER BY DAY, AMPM DESC;
-- 3. 해당일 메뉴
SELECT * FROM LUNCH WHERE TO_CHAR(LDATE, 'YYYY-MM-DD') = '2022'|| '-' || '07'||'-'||'04' AND AMPM='석식'; 
COMMIT;