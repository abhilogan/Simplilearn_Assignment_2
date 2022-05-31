package in.co.school.mgt.bean;

public class FeeBean extends BaseBean {
	
	private long studentId;
	private String studentName;
	private long pay;
	private long totalfee;
	private long paid;
	private long due;
	private long schoolCode;
	
	

	public long getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(long schoolCode) {
		this.schoolCode = schoolCode;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public long getPay() {
		return pay;
	}

	public void setPay(long pay) {
		this.pay = pay;
	}

	public long getTotalfee() {
		return totalfee;
	}

	public void setTotalfee(long totalfee) {
		this.totalfee = totalfee;
	}

	public long getPaid() {
		return paid;
	}

	public void setPaid(long paid) {
		this.paid = paid;
	}

	public long getDue() {
		return due;
	}

	public void setDue(long due) {
		this.due = due;
	}

	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
