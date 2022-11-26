package kr.smhrd.model;

public class LcourseVO {
   private int lnum; 
   private String lyear;
   private String lcode;
   private String lname;
public int getLnum() {
	return lnum;
}
public void setLnum(int lnum) {
	this.lnum = lnum;
}
public String getLyear() {
	return lyear;
}
public void setLyear(String lyear) {
	this.lyear = lyear;
}
public String getLcode() {
	return lcode;
}
public void setLcode(String lcode) {
	this.lcode = lcode;
}
public String getLname() {
	return lname;
}
public void setLname(String lname) {
	this.lname = lname;
}
@Override
public String toString() {
	return "LcourseVO [lnum=" + lnum + ", lyear=" + lyear + ", lcode=" + lcode + ", lname=" + lname + "]";
}
   
}
