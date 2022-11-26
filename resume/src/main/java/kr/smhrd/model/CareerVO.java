package kr.smhrd.model;

public class CareerVO {
	   private int num;
	   private int cnum;
	   private String lcode;
	   private String mnum;
	   private String cpart;
	   private String cloc;
	   private String cskill;
	   private String ccontent;
	   private String csave;
	   private String cstart;
	   private String cinsur;
	   public CareerVO() {   } 
	   
	   
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


	public int getNum() {
		return num;
	}


	public String getCcontent() {
		return ccontent;
	}

	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}

	public void setNum(int num) {
		this.num = num;
	}


	public int getCnum() {
		return cnum;
	}


	public void setCnum(int cnum) {
		this.cnum = cnum;
	}


	public String getCpart() {
		return cpart;
	}


	public void setCpart(String cpart) {
		this.cpart = cpart;
	}


	public String getCloc() {
		return cloc;
	}


	public void setCloc(String cloc) {
		this.cloc = cloc;
	}


	public String getCskill() {
		return cskill;
	}


	public void setCskill(String cskill) {
		this.cskill = cskill;
	}


	public String getCsave() {
		return csave;
	}


	public void setCsave(String csave) {
		this.csave = csave;
	}


	public String getCstart() {
		return cstart;
	}


	public void setCstart(String cstart) {
		this.cstart = cstart;
	}


	public String getCinsur() {
		return cinsur;
	}


	public void setCinsur(String cinsur) {
		this.cinsur = cinsur;
	}


	@Override
	public String toString() {
		return "CareerVO [num=" + num + ", cnum=" + cnum + ", cpart=" + cpart + ", cloc=" + cloc + ", cskill=" + cskill
				+ ", csave=" + csave + ", cstart=" + cstart + ", cinsur=" + cinsur + "]";
	}
	   
}
