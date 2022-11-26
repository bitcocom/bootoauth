package kr.smhrd.model;

import java.util.Date;

public class TimeVO {
    private int tnum; 	// 번호 
    private String tday; // 1일차
    private Date tdate; // 날짜
    private String tmon; // 요일
    private String tstep; // 교시
    private String ttime; //시간
    private String tcourse; //과목명
    private String tteach; //강사명
    private String treal; //장소
    private String tinc; //누적시간
	public int getTnum() {
		return tnum;
	}
	public void setTnum(int tnum) {
		this.tnum = tnum;
	}
	public String getTday() {
		return tday;
	}
	public void setTday(String tday) {
		this.tday = tday;
	}
	
	
	public Date getTdate() {
		return tdate;
	}
	public void setTdate(Date tdate) {
		this.tdate = tdate;
	}
	public String getTmon() {
		return tmon;
	}
	public void setTmon(String tmon) {
		this.tmon = tmon;
	}
	public String getTstep() {
		return tstep;
	}
	public void setTstep(String tstep) {
		this.tstep = tstep;
	}
	public String getTtime() {
		return ttime;
	}
	public void setTtime(String ttime) {
		this.ttime = ttime;
	}
	public String getTcourse() {
		return tcourse;
	}
	public void setTcourse(String tcourse) {
		this.tcourse = tcourse;
	}
	public String getTteach() {
		return tteach;
	}
	public void setTteach(String tteach) {
		this.tteach = tteach;
	}
	public String getTreal() {
		return treal;
	}
	public void setTreal(String treal) {
		this.treal = treal;
	}
	public String getTinc() {
		return tinc;
	}
	public void setTinc(String tinc) {
		this.tinc = tinc;
	}
	@Override
	public String toString() {
		return "TimeVO [tnum=" + tnum + ", tday=" + tday + ", tdate=" + tdate + ", tmon=" + tmon + ", tstep=" + tstep
				+ ", ttime=" + ttime + ", tcourse=" + tcourse + ", tteach=" + tteach + ", treal=" + treal + ", tinc=" + tinc
				+ "]";
	}   
}
