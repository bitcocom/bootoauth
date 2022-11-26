package kr.smhrd.model;

public class McourseVO {
	  private int mnum;
	  private String lcode;
	  private String mname;
	  private String mday;
	  private int mold;
	  private int msu;
	  private String mmaster;
	  private String mta;
	  private String mlocation;
	  private int msu1;
	  private int msu2;
	  private int msu1per;
	  private int msu2per;
	  private String lname;
	  private String lyear;
	  private String isdata;
	  private String ismeet;
	  private String misis;
	  private String istime;
	  
	  public String getIstime() {
		return istime;
	}

	public void setIstime(String istime) {
		this.istime = istime;
	}

	public String getMisis() {
		return misis;
	}

	public void setMisis(String misis) {
		this.misis = misis;
	}

	public McourseVO() {   }
	
	public int getMold() {
		return mold;
	}

	public void setMold(int mold) {
		this.mold = mold;
	}

	public int getMsu1() {
		return msu1;
	}

	public void setMsu1(int msu1) {
		this.msu1 = msu1;
	}

	public int getMsu2() {
		return msu2;
	}

	public void setMsu2(int msu2) {
		this.msu2 = msu2;
	}

	public int getMsu1per() {
		return msu1per;
	}

	public void setMsu1per(int msu1per) {
		this.msu1per = msu1per;
	}

	public int getMsu2per() {
		return msu2per;
	}

	public void setMsu2per(int msu2per) {
		this.msu2per = msu2per;
	}

	public String getIsmeet() {
		return ismeet;
	}

	public void setIsmeet(String ismeet) {
		this.ismeet = ismeet;
	}

	public int getMnum() {
		return mnum;
	}
	public void setMnum(int mnum) {
		this.mnum = mnum;
	}
	public String getLcode() {
		return lcode;
	}
	public void setLcode(String lcode) {
		this.lcode = lcode;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMday() {
		return mday;
	}
	public void setMday(String mday) {
		this.mday = mday;
	}
	public int getMsu() {
		return msu;
	}
	public void setMsu(int msu) {
		this.msu = msu;
	}
	public String getMmaster() {
		return mmaster;
	}
	public void setMmaster(String mmaster) {
		this.mmaster = mmaster;
	}
	public String getMta() {
		return mta;
	}
	public void setMta(String mta) {
		this.mta = mta;
	}
	public String getMlocation() {
		return mlocation;
	}
	public void setMlocation(String mlocation) {
		this.mlocation = mlocation;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getLyear() {
		return lyear;
	}
	public void setLyear(String lyear) {
		this.lyear = lyear;
	}
	
	public String getIsdata() {
		return isdata;
	}


	public void setIsdata(String isdata) {
		this.isdata = isdata;
	}

	@Override
	public String toString() {
		return "McourseVO [mnum=" + mnum + ", lcode=" + lcode + ", mname=" + mname + ", mday=" + mday + ", mold=" + mold
				+ ", msu=" + msu + ", mmaster=" + mmaster + ", mta=" + mta + ", mlocation=" + mlocation + ", msu1="
				+ msu1 + ", msu2=" + msu2 + ", msu1per=" + msu1per + ", msu2per=" + msu2per + ", lname=" + lname
				+ ", lyear=" + lyear + ", isdata=" + isdata + ", ismeet=" + ismeet + "]";
	}
}
