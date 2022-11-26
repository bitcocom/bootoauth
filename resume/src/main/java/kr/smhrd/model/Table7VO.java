package kr.smhrd.model;

public class Table7VO {
	  private int tnum7;
	  private int num;
	  private String lcode;
	  private String mnum;
	  private String scontent7;
	public int getTnum7() {
		return tnum7;
	}
	public void setTnum7(int tnum7) {
		this.tnum7 = tnum7;
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
	public String getScontent7() {
		return scontent7;
	}
	public void setScontent7(String scontent7) {
		this.scontent7 = scontent7;
	}
	@Override
	public String toString() {
		return "Table7VO [tnum7=" + tnum7 + ", num=" + num + ", lcode=" + lcode + ", mnum=" + mnum + ", scontent7="
				+ scontent7 + "]";
	}
	
}
