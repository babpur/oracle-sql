package vo;

// VO: Value Object > DTO: Date Transfer Object, Domain
// ㄴ DTO는 VO에 속함
// table 속성을 가지면 domain이라고 부를 수 있다.

public class Dept {
	
	private int deptNo;
	private String dname;
	private String loc;
	
	public int getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	

}
