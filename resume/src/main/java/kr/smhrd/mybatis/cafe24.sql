-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.3.27-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 테이블 test.career 구조 내보내기
CREATE TABLE IF NOT EXISTS `career` (
  `cseq` int(11) NOT NULL AUTO_INCREMENT,
  `cnum` int(11) NOT NULL,
  `lcode` varchar(20) NOT NULL,
  `mnum` varchar(20) NOT NULL,
  `cpart` varchar(20) DEFAULT '0',
  `cloc` varchar(20) DEFAULT '0',
  `cskill` varchar(20) DEFAULT '0',
  `ccontent` varchar(4000) DEFAULT NULL,
  `csave` varchar(100) DEFAULT '0',
  `cstart` varchar(100) DEFAULT '0',
  `cinsur` varchar(1000) DEFAULT '0',
  PRIMARY KEY (`cseq`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 test.lcourse 구조 내보내기
CREATE TABLE IF NOT EXISTS `lcourse` (
  `lnum` int(11) NOT NULL AUTO_INCREMENT,
  `lyear` varchar(20) NOT NULL,
  `lcode` varchar(20) NOT NULL,
  `lname` varchar(100) NOT NULL,
  PRIMARY KEY (`lnum`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 test.mcourse 구조 내보내기
CREATE TABLE IF NOT EXISTS `mcourse` (
  `mnum` int(11) NOT NULL AUTO_INCREMENT,
  `lcode` varchar(30) NOT NULL,
  `mname` varchar(4000) NOT NULL,
  `mday` varchar(100) NOT NULL,
  `mold` int(11) NOT NULL,
  `msu` int(11) NOT NULL,
  `mmaster` varchar(50) NOT NULL,
  `mta` varchar(50) NOT NULL,
  `mlocation` varchar(50) NOT NULL,
  `msu1` int(11) DEFAULT 0,
  `msu2` int(11) DEFAULT 0,
  `msu1per` float(5,2) DEFAULT 0.00,
  `msu2per` int(11) DEFAULT 0,
  `misis` int(11) DEFAULT 0,
  `isdata` varchar(50) DEFAULT 'NO',
  `ismeet` varchar(50) DEFAULT 'NO',
  `istime` varchar(100) DEFAULT 'NO',
  PRIMARY KEY (`mnum`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 test.mentor 구조 내보내기
CREATE TABLE IF NOT EXISTS `mentor` (
  `mtnum` int(11) NOT NULL AUTO_INCREMENT,
  `mtname` varchar(100) NOT NULL,
  `mtsosk` varchar(1000) NOT NULL,
  `mttel` varchar(100) NOT NULL,
  `mtemail` varchar(100) NOT NULL,
  `mtmajor` varchar(100) NOT NULL,
  `mtpart` varchar(1000) NOT NULL,
  `mtlevel` varchar(100) DEFAULT 'D',
  `mtimage` varchar(1000) DEFAULT 'NO',
  PRIMARY KEY (`mtnum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 test.resume 구조 내보내기
CREATE TABLE IF NOT EXISTS `resume` (
  `num` int(11) NOT NULL AUTO_INCREMENT,
  `lcode` varchar(20) NOT NULL,
  `mnum` varchar(20) NOT NULL,
  `name` varchar(30) NOT NULL,
  `ssn` varchar(30) NOT NULL,
  `tel` varchar(30) NOT NULL,
  `addr` varchar(1000) DEFAULT 'NO',
  `email` varchar(30) NOT NULL,
  `sex` varchar(30) NOT NULL,
  `birth` varchar(30) NOT NULL,
  `college` varchar(100) NOT NULL,
  `major` varchar(50) DEFAULT '고등학교',
  `state1` varchar(50) DEFAULT 'NO',
  `state2` varchar(50) DEFAULT 'NO',
  `image` varchar(1000) DEFAULT 'NO',
  `cert` varchar(1000) DEFAULT 'NO',
  `cnt` int(11) DEFAULT 0,
  `isis` int(11) DEFAULT 1,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 test.sangdam 구조 내보내기
CREATE TABLE IF NOT EXISTS `sangdam` (
  `snum` int(11) NOT NULL AUTO_INCREMENT,
  `num` int(11) NOT NULL,
  `lcode` varchar(30) NOT NULL,
  `mnum` varchar(30) NOT NULL,
  `sdate` date DEFAULT NULL,
  `scontent` varchar(1000) NOT NULL,
  `sname` varchar(100) NOT NULL,
  `cert` varchar(100) NOT NULL DEFAULT '-',
  PRIMARY KEY (`snum`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 test.table1 구조 내보내기
CREATE TABLE IF NOT EXISTS `table1` (
  `tnum` int(11) NOT NULL AUTO_INCREMENT,
  `num` int(11) NOT NULL,
  `lcode` varchar(30) NOT NULL,
  `mnum` varchar(30) NOT NULL,
  `sdate` varchar(300) DEFAULT NULL,
  `student` varchar(1000) NOT NULL,
  `smajor` varchar(100) NOT NULL,
  `scong` varchar(100) NOT NULL,
  PRIMARY KEY (`tnum`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 test.table2 구조 내보내기
CREATE TABLE IF NOT EXISTS `table2` (
  `tnum2` int(11) NOT NULL AUTO_INCREMENT,
  `num` int(11) NOT NULL,
  `lcode` varchar(30) NOT NULL,
  `mnum` varchar(30) NOT NULL,
  `sdate2` varchar(300) DEFAULT NULL,
  `company2` varchar(1000) NOT NULL,
  `sjob2` varchar(100) NOT NULL,
  `sconti2` varchar(100) NOT NULL,
  PRIMARY KEY (`tnum2`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 test.table3 구조 내보내기
CREATE TABLE IF NOT EXISTS `table3` (
  `tnum3` int(11) NOT NULL AUTO_INCREMENT,
  `num` int(11) NOT NULL,
  `lcode` varchar(30) NOT NULL,
  `mnum` varchar(30) NOT NULL,
  `sdate3` varchar(300) DEFAULT NULL,
  `scourse3` varchar(1000) NOT NULL,
  `sname3` varchar(100) NOT NULL,
  PRIMARY KEY (`tnum3`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 test.table4 구조 내보내기
CREATE TABLE IF NOT EXISTS `table4` (
  `tnum4` int(11) NOT NULL AUTO_INCREMENT,
  `num` int(11) NOT NULL,
  `lcode` varchar(30) NOT NULL,
  `mnum` varchar(30) NOT NULL,
  `sdate4` varchar(300) DEFAULT NULL,
  `scert4` varchar(1000) NOT NULL,
  `ssihang4` varchar(100) NOT NULL,
  PRIMARY KEY (`tnum4`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 test.table5 구조 내보내기
CREATE TABLE IF NOT EXISTS `table5` (
  `tnum5` int(11) NOT NULL AUTO_INCREMENT,
  `num` int(11) NOT NULL,
  `lcode` varchar(30) NOT NULL,
  `mnum` varchar(30) NOT NULL,
  `skill5` varchar(300) DEFAULT NULL,
  `slevel5` varchar(1000) NOT NULL,
  `scontent5` varchar(1000) NOT NULL,
  PRIMARY KEY (`tnum5`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 test.table6 구조 내보내기
CREATE TABLE IF NOT EXISTS `table6` (
  `tnum6` int(11) NOT NULL AUTO_INCREMENT,
  `num` int(11) NOT NULL,
  `lcode` varchar(30) NOT NULL,
  `mnum` varchar(30) NOT NULL,
  `sdate6` varchar(300) DEFAULT NULL,
  `stitle6` varchar(1000) NOT NULL,
  `scontent6` varchar(1000) NOT NULL,
  PRIMARY KEY (`tnum6`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 test.table7 구조 내보내기
CREATE TABLE IF NOT EXISTS `table7` (
  `tnum7` int(11) NOT NULL AUTO_INCREMENT,
  `num` int(11) NOT NULL,
  `lcode` varchar(30) NOT NULL,
  `mnum` varchar(30) NOT NULL,
  `scontent7` varchar(4000) NOT NULL,
  PRIMARY KEY (`tnum7`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 test.tb_attach 구조 내보내기
CREATE TABLE IF NOT EXISTS `tb_attach` (
  `idx` int(11) NOT NULL COMMENT '게시물아이디',
  `seq` int(11) DEFAULT NULL COMMENT '순번',
  `filename` varchar(256) DEFAULT NULL COMMENT '파일명',
  `writer` varchar(50) DEFAULT NULL COMMENT '등록자',
  `indate` datetime DEFAULT NULL COMMENT '등록일',
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='첨부파일';

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 test.tb_board 구조 내보내기
CREATE TABLE IF NOT EXISTS `tb_board` (
  `idx` int(11) NOT NULL AUTO_INCREMENT COMMENT '게시물아이디',
  `title` varchar(100) DEFAULT NULL COMMENT '제목',
  `contents` varchar(3000) DEFAULT NULL COMMENT '내용',
  `count` int(11) DEFAULT NULL COMMENT '조회수',
  `writer` varchar(50) DEFAULT NULL COMMENT '등록자',
  `indate` datetime DEFAULT NULL COMMENT '등록일',
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='게시판';

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 test.tb_reply 구조 내보내기
CREATE TABLE IF NOT EXISTS `tb_reply` (
  ` idx` int(11) NOT NULL COMMENT '게시물아이디',
  `seq` int(11) NOT NULL COMMENT '순번',
  `reply` varchar(1000) DEFAULT NULL COMMENT '댓글',
  `writer` varchar(50) DEFAULT NULL COMMENT '작성자',
  `indate` datetime DEFAULT NULL COMMENT '작성일',
  PRIMARY KEY (` idx`,`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='댓글';

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 test.tb_user 구조 내보내기
CREATE TABLE IF NOT EXISTS `tb_user` (
  `user_id` varchar(30) NOT NULL COMMENT '아이디',
  `user_name` varchar(50) DEFAULT NULL COMMENT '이름',
  `password` varchar(20) DEFAULT NULL COMMENT '비밀번호',
  `use_yn` varchar(1) DEFAULT NULL COMMENT '사용여부',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='사용자';

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 test.team 구조 내보내기
CREATE TABLE IF NOT EXISTS `team` (
  `num` int(11) NOT NULL,
  `lcode` varchar(20) NOT NULL,
  `mnum` varchar(20) NOT NULL,
  `name` varchar(30) NOT NULL,
  `college` varchar(1000) NOT NULL,
  `ssn` varchar(30) NOT NULL,
  `team` int(11) DEFAULT 0,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
select * from mcourse;
delete from lcourse;
delete from mcourse;
insert into lcourse(lyear,lcode,lname) values('2020', '0', '전체운영과정');
insert into lcourse(lyear,lcode,lname) values('2020', '1', '혁신성장인재양성');
insert into lcourse(lyear,lcode,lname) values('2020', '2', '4차산업혁명인력양성');
insert into lcourse(lyear,lcode,lname) values('2020', '3', '청년취업아카데미');
insert into lcourse(lyear,lcode,lname) values('2020', '4', '디지털 핵심 실무인재 양성사업');
