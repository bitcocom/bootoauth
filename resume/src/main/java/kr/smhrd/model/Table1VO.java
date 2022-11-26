package kr.smhrd.model;

public class Table1VO {
	  private int tnum;
	  private int num;
	  private String lcode;
	  private String mnum;
	  private String t1Sdate;
	  private String t1Edate;
	  private String sdate;
	  private String student;
	  private String smajor;
	  private String scong;
	public int getTnum() {
		return tnum;
	}
	public void setTnum(int tnum) {
		this.tnum = tnum;
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
	public String getT1Sdate() {
		return t1Sdate;
	}
	public void setT1Sdate(String t1Sdate) {
		this.t1Sdate = t1Sdate;
	}
	public String getT1Edate() {
		return t1Edate;
	}
	public void setT1Edate(String t1Edate) {
		this.t1Edate = t1Edate;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getStudent() {
		return student;
	}
	public void setStudent(String student) {
		this.student = student;
	}
	public String getSmajor() {
		return smajor;
	}
	public void setSmajor(String smajor) {
		this.smajor = smajor;
	}
	public String getScong() {
		return scong;
	}
	public void setScong(String scong) {
		this.scong = scong;
	}
	@Override
	public String toString() {
		return "Table1VO [tnum=" + tnum + ", num=" + num + ", lcode=" + lcode + ", mnum=" + mnum + ", t1Sdate="
				+ t1Sdate + ", t1Edate=" + t1Edate + ", sdate=" + sdate + ", student=" + student + ", smajor=" + smajor
				+ ", scong=" + scong + "]";
	}
	  
}
