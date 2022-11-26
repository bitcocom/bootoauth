package kr.smhrd.model;
public class TeamVO {
	  private int num;
	  private String lcode;
	  private int mnum;
	  private String name;
	  private int team;
	  private String  college;
	  private String ssn;
	  
	
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
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
	public int getMnum() {
		return mnum;
	}
	public void setMnum(int mnum) {
		this.mnum = mnum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTeam() {
		return team;
	}
	public void setTeam(int team) {
		this.team = team;
	}
	@Override
	public String toString() {
		return "TeamVO [num=" + num + ", lcode=" + lcode + ", mnum=" + mnum + ", name=" + name + ", team=" + team
				+ ", college=" + college + ", ssn=" + ssn + "]";
	}
	
	
	  
}
