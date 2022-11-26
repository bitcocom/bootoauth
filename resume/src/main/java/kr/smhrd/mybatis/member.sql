-- 스마트훈련시스템 --
drop table resume;
drop table lcourse;
drop table mcourse;
select * from lcourse;
delete from lcourse;
select * from mcourse;
drop table sangdam;
drop table team;
drop table mentor;
drop table career;
drop table table1;
drop table table2;
drop table table3;
drop table table4;
drop table table5;
drop table table6;
drop table table7;

create table resume(
  num number not null primary key, -- 학생일련번호
  lcode varchar2(20) not null, -- 대분류번호
  mnum varchar2(20) not null, -- 중분류번호
  name varchar2(30) not null, -- 학생이름
  ssn varchar2(30) not null, -- 주민번호
  tel varchar2(30) not null, -- 전화번호
  addr varchar2(1000) default 'NO', -- 주소
  email varchar2(30) not null, -- 이메일
  sex varchar2(30) not null, -- 성별
  birth varchar2(30) not null, -- 생년월일
  college varchar2(100) not null, -- 학교
  major varchar2(50) default '고등학교', -- 전공
  state1 varchar2(50) default 'NO', -- 수료상태
  state2 varchar2(50) default 'NO', -- 미취업상태
  image varchar2(1000) default 'NO', -- 이미지이름
  cert varchar2(1000) default 'NO', -- 자격증
  cnt number default 0, -- 상담횟수
  -- 중도탈락 여부 체크
  isis number default 1
);

-- ALTER TABLE resume ADD isis number default 1;

create sequence resume_seq;

-- drop table resume;
-- update resume set state1='YES' where num=#{num}   
-- select * from resume where name='김민희';
-- update resume set isis=1 where num=770;
-- delete from resume;

-- 대분류 --
create table lcourse(
   lnum number primary key not null, -- 일련번호
   lyear varchar2(20) not null, -- 년도
   lcode varchar2(20) not null, -- 대분류번호
   lname varchar2(100) not null  -- 분류명  
);

-- ALTER TABLE lcourse MODIFY(lname VARCHAR2(100));
create sequence lnum_seq;

insert into lcourse values(lnum_seq.nextval, '2020', '0', '전체운영과정');
insert into lcourse values(lnum_seq.nextval, '2020', '1', '혁신성장인재양성');
insert into lcourse values(lnum_seq.nextval, '2020', '2', '4차산업혁명인력양성');
insert into lcourse values(lnum_seq.nextval, '2020', '3', '청년취업아카데미');
insert into lcourse values(lnum_seq.nextval, '2020', '4', '디지털 핵심 실무인재 양성사업');

--delete from lcourse;

--SELECT * from lcourse;

--update LCOURSE set lname='혁신성장인재양성' where lcode=1;
--update LCOURSE set lname='4차산업혁명인력양성' where lcode=2;
--update LCOURSE set lname='청년취업아카데미' where lcode=3;

-- 중분류 --
create table mcourse(
  mnum number primary key not null, -- 중분류번호(일련번호)
  lcode varchar2(30) not null, -- 대분류번호
  mname varchar2(4000) not null, -- 과정명
  mday varchar2(100) not null, -- 교육기간
  mold number not null, -- 승인인원
  msu number not null, -- 교육인원
  mmaster varchar2(50) not null, -- 담임
  mta varchar2(50) not null, -- TA
  mlocation varchar2(50) not null, --, -- 교육실
  msu1 number default 0, -- 수료인원
  msu2 number default 0, -- 취업인원
  msu1per number(3,2) default 0.0, -- 수료율
  msu2per number default 0, -- 취업률
  misis number default 0, -- 중탈인원
  isdata varchar2(50) default 'NO', -- 학생등록여부
  ismeet varchar2(50) default 'NO', -- 상담카드작성여부(X)
  istime varchar2(100) default 'NO' -- 시간표파일
  -- lname varchar2(30) not null, -- 분류명
  -- lyear varchar2(20) not null -- 년도
);
create sequence mnum_seq;
--ALTER TABLE mcourse MODIFY(mname VARCHAR2(2000));

--desc mcourse;

--ALTER TABLE mcourse ADD misis number default 0;

--ALTER TABLE mcourse MODIFY(msu1per number(3,2) default 0.0);

--drop table mcourse;

--insert into mcourse(mnum,lcode,mname,mday,msu,mmaster,mta,mlocation) values(mnum_seq.nextval, 2, '스마트그리드','2020.11.20~2020.12.30',30, '나예호' ,'미정', '교육실4');
--insert into mcourse(mnum,lcode,mname,mday,msu,mmaster,mta,mlocation) values(mnum_seq.nextval, 2, '빅데이터','2020.11.20~2020.12.30',30, '나예호' ,'미정', '교육실4');
--insert into mcourse(mnum,lcode,mname,mday,msu,mmaster,mta,mlocation) values(mnum_seq.nextval, 2, '인공지능','2020.11.20~2020.12.30',30, '나예호' ,'미정', '교육실4');
--delete from mcourse;
--select * from mcourse;
--update mcourse set misis=0 where mnum=41;
-- select * from mcourse order by lcode
--update mcourse set mname='스마트그리드기반 IoT융합 SW전문가과정(2회차)' where mnum=13;
--ALTER TABLE mcourse MODIFY(mname VARCHAR2(4000));
--update mcourse set ismeet='NO' where mnum=13 and lcode=2;
--update mcourse set isdata='NO' where mnum=13 and lcode=2;
--select mname from mcourse where lcode=2 and mnum=13  
update mcourse set istime='NO' where mnum=4 and lcode=4;


-- 상담내역 작성하기
create table sangdam(
  snum number not null primary key, -- 상담일련번호
  num number not null, -- 학생번호
  lcode varchar2(30) not null, -- 대분류번호
  mnum varchar2(30) not null, -- 중분류번호
  sdate DATE, -- 날짜
  scontent varchar2(1000) not null, -- 상담내용
  sname varchar2(100) not null,
  cert varchar2(100) default '-' not null
);
create sequence snum_seq;
--alter table sangdam add cert varchar2(100) default '-' not null;
--select * from sangdam;
--delete from sangdam;
--select num, count(scontent) as cnt from (
--select r.num, s.scontent from RESUME r, SANGDAM s where r.num=s.num and r.lcode=s.lcode and r.mnum=s.mnum  order by r.name
--) group by num;

-- team table 

create table team(
  num number not null primary key, -- 학생일련번호
  lcode varchar2(20) not null, -- 대분류번호
  mnum varchar2(20) not null, -- 중분류번호
  name varchar2(30) not null, -- 학생이름
  college varchar2(1000) not null,
  ssn varchar2(30) not null,
  team number  default 0 -- 팀번호 
 );
create sequence team_seq;
--drop table team;

--select * from team; 
--delete from team;

-- 멘토 table

create table mentor(
  mtnum number not null primary key, -- 멘토 일련번호
  mtname varchar2(100) not null, -- 멘토 이름
  mtsosk varchar2(1000) not null, -- 멘토 소속
  mttel varchar2(100) not null, -- 멘토 전화번호
  mtemail varchar2(100) not null, -- 멘토 이메일
  mtmajor varchar2(100) not null, -- 멘토 전공
  mtpart varchar2(1000) not null, -- 멘토 가능 분야
  mtlevel varchar2(100) default 'D' , -- 멘토 자체등급(A, B, C, D:기본등급)
  mtimage varchar2(1000) default 'NO'
);
create sequence mtnum_seq;
--drop table mentor;
--select * from mentor
-- 멘토링

-- 멘토 1명 + 여러 멘티

create table career(
   cseq number not null, -- 일련번호
   cnum number not null, -- 학생번호
   lcode varchar2(20) not null, -- 대분류번호
   mnum varchar2(20) not null, -- 중분류번호
   cpart varchar2(20) default 0, -- 희망분야
   cloc varchar2(20) default 0, -- 희망지역
   cskill varchar2(20) default 0, -- 기술수준
   ccontent varchar2(4000) null, -- 연계내용
   csave varchar2(100) default 0, -- 기업명
   cstart varchar2(100) default 0, -- 근무시작일
   cinsur varchar2(1000) default 0 -- 4대보험가입일
);
create sequence career_num;

--drop table career;

--select * from career;

--select * from RESUME r, career c where r.num=c.cnum and r.num=11;

-- select
--    num,
--	r.lcode,
--	r.mnum,
--	name,
--	ssn,
--	tel,
--	email,
--	sex,
--	birth,
--	college,
--	major,
--	state1,
--	state2,
--	image,
--	cert,
--	isis,
--	cpart,
--	cloc,
--	cskill,
--	ccontent,
--	csave,
--	cstart,
--	cinsur
--    from RESUME r, career c where r.num=c.cnum and r.num=1041


--delete from career;

--select * from career where cnum=1081

--학력사항 table1자굑
create table table1(
  tnum number not null primary key, -- 상담일련번호
  num number not null, -- 학생번호
  lcode varchar2(30) not null, -- 대분류번호
  mnum varchar2(30) not null, -- 중분류번호
  sdate varchar2(300), -- 년도날짜
  student varchar2(1000) not null, -- 학교명
  smajor varchar2(100) not null, -- 전공
  scong varchar2(100) not null -- 졸업여부
);
create sequence table1_num;
--select * from RESUME r, table1 t1, table2 t2 where r.num=1081 or r.num=t1.num or r.num=t2.num;
--
--SELECT * FROM  (select * from RESUME r, table1 t1 where r.num=t1.num(+)) as t3, table2 t2 where t3.num=t2.num as t4)
--where t4.num=1081
--
--
--select * from (select * from RESUME r, table1 t1 where r.num=t1.num(+) t3, table2 t2 where t3.num=t2.num))
--
--select * from (select r.num,  from RESUME r, table1 t1 where r.num=t1.num(+) and r.num=1081), table2 t2 where num=t2.num(+)
--
--
--select * from RESUME r, table1 t1 where r.num=t1.num(+) and r.num=1081
--select * from RESUME r, table2 t2 where r.num=t2.num(+) and r.num=1081   

--select * from table1;
--drop table table1;

--경력사항 table2
create table table2(
  tnum2 number not null primary key, -- 상담일련번호
  num number not null, -- 학생번호
  lcode varchar2(30) not null, -- 대분류번호
  mnum varchar2(30) not null, -- 중분류번호
  sdate2 varchar2(300), -- 근무기간
  company2 varchar2(1000) not null, -- 근무처
  sjob2 varchar2(100) not null, -- 담당업무
  sconti2 varchar2(100) not null -- 근속연수
);
create sequence table2_num;

--select * from table2;

-- 교육사항 table3
create table table3(
  tnum3 number not null primary key, -- 상담일련번호
  num number not null, -- 학생번호
  lcode varchar2(30) not null, -- 대분류번호
  mnum varchar2(30) not null, -- 중분류번호
  sdate3 varchar2(300), -- 날짜
  scourse3 varchar2(1000) not null, -- 상담내용
  sname3 varchar2(100) not null
);
create sequence table3_num;

--select * from RESUME r, table3 t3 where r.num=t3.num(+) and r.num=1081
--select * from table3;
--delete from table3;

-- 자격사항 table4
create table table4(
  tnum4 number not null primary key, -- 상담일련번호
  num number not null, -- 학생번호
  lcode varchar2(30) not null, -- 대분류번호
  mnum varchar2(30) not null, -- 중분류번호
  sdate4 varchar2(300), -- 날짜
  scert4 varchar2(1000) not null, -- 상담내용
  ssihang4 varchar2(100) not null
);
create sequence table4_num;

--select * from table4;
--select * from RESUME r, table4 t4 where r.num=t4.num(+) and r.num=1081

 -- 보유기술 table5
create table table5(
  tnum5 number not null primary key, -- 상담일련번호
  num number not null, -- 학생번호
  lcode varchar2(30) not null, -- 대분류번호
  mnum varchar2(30) not null, -- 중분류번호
  skill5 varchar2(300), -- 날짜
  slevel5 varchar2(1000) not null, -- 상담내용
  scontent5 varchar2(1000) not null
);
create sequence table5_num;


-- 프로젝트 table6
create table table6(
  tnum6 number not null primary key, -- 상담일련번호
  num number not null, -- 학생번호
  lcode varchar2(30) not null, -- 대분류번호
  mnum varchar2(30) not null, -- 중분류번호
  sdate6 varchar2(300), -- 날짜
  stitle6 varchar2(1000) not null, -- 상담내용
  scontent6 varchar2(1000) not null
);
create sequence table6_num;

-- 자기소개서
create table table7(
  tnum7 number not null primary key, -- 상담일련번호
  num number not null, -- 학생번호
  lcode varchar2(30) not null, -- 대분류번호
  mnum varchar2(30) not null, -- 중분류번호
  scontent7 varchar2(4000) not null
);
create sequence table7_num;

--select * from resume;
--select * from mcourse;
--select * from sangdam;
--select * from career;
--select * from table1;
--select * from table2;
--select * from table3;
--select * from table4;
--select * from table5;
--select * from table6;
--
--select * from table7;
--delete from table1;
--delete from table2;
--delete from table3;
--delete from table4;
--delete from table5;
--delete from table6;
--delete from table7;
