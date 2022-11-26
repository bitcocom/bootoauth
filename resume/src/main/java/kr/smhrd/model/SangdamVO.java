package kr.smhrd.model;

import java.sql.Date;

public class SangdamVO {
	private int snum; // 상담일련번호
	private int num; // 학생번호
	private String lcode; // 대분류번호
	private String mnum; //중분류번호
	private Date sdate; // 날짜
	private String scontent; // 상담내용
	private String sname; // 상담자
	private String cert; //자격현황
	
	public String getCert() {
		return cert;
	}
	public void setCert(String cert) {
		this.cert = cert;
	}
	public int getSnum() {
		return snum;
	}
	public void setSnum(int snum) {
		this.snum = snum;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getLcode() {
		return lcode;
	}
	public void setLcode(String lcode) {
		this.lcode = lcode;
	}
	public String getMnum() {
		return mnum;
	}
	public void setMnum(String mnum) {
		this.mnum = mnum;
	}
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public String getScontent() {
		return scontent;
	}
	public void setScontent(String scontent) {
		this.scontent = scontent;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	@Override
	public String toString() {
		return "SangdamVO [snum=" + snum + ", num=" + num + ", lcode=" + lcode + ", mnum=" + mnum + ", sdate=" + sdate
				+ ", scontent=" + scontent + ", sname=" + sname + "]";
	}	
}
