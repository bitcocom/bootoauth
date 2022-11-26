package kr.smhrd.model;

public class MentorVO {
	  private int mtnum; // 멘토 일련번호
	  private String mtname; // 멘토 이름
	  private String mtsosk; // 멘토 소속
	  private String mttel; // 멘토 전화번호
	  private String mtemail; // 멘토 이메일
	  private String mtmajor; // 멘토 전공
	  private String mtpart; // 멘토 가능 분야
	  private String mtlevel; //멘토 자체등급(A, B, C, D:기본등급)
	  private String mtimage; //멘토사진
	public int getMtnum() {
		return mtnum;
	}
	public void setMtnum(int mtnum) {
		this.mtnum = mtnum;
	}
	public String getMtname() {
		return mtname;
	}
	public void setMtname(String mtname) {
		this.mtname = mtname;
	}
	public String getMtsosk() {
		return mtsosk;
	}
	public void setMtsosk(String mtsosk) {
		this.mtsosk = mtsosk;
	}
	public String getMttel() {
		return mttel;
	}
	public void setMttel(String mttel) {
		this.mttel = mttel;
	}
	public String getMtemail() {
		return mtemail;
	}
	public void setMtemail(String mtemail) {
		this.mtemail = mtemail;
	}
	public String getMtmajor() {
		return mtmajor;
	}
	public void setMtmajor(String mtmajor) {
		this.mtmajor = mtmajor;
	}
	public String getMtpart() {
		return mtpart;
	}
	public void setMtpart(String mtpart) {
		this.mtpart = mtpart;
	}
	public String getMtlevel() {
		return mtlevel;
	}
	public void setMtlevel(String mtlevel) {
		this.mtlevel = mtlevel;
	}
	public String getMtimage() {
		return mtimage;
	}
	public void setMtimage(String mtimage) {
		this.mtimage = mtimage;
	}
	@Override
	public String toString() {
		return "MentorVO [mtnum=" + mtnum + ", mtname=" + mtname + ", mtsosk=" + mtsosk + ", mttel=" + mttel
				+ ", mtemail=" + mtemail + ", mtmajor=" + mtmajor + ", mtpart=" + mtpart + ", mtlevel=" + mtlevel
				+ ", mtimage=" + mtimage + "]";
	}
	  
}
