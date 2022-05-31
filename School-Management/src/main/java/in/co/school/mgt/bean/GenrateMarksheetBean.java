package in.co.school.mgt.bean;

public class GenrateMarksheetBean  extends BaseBean{
	
	private long studentId;
	private String name;
	private long classId;
	private String className;
	private long schoolCode;
	private long totalCreadit;
	private long total;
	private double CGPA;
	private long rollNo;
	
	
	public long getRollNo() {
		return rollNo;
	}
	public void setRollNo(long rollNo) {
		this.rollNo = rollNo;
	}
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getClassId() {
		return classId;
	}
	public void setClassId(long classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public long getSchoolCode() {
		return schoolCode;
	}
	public void setSchoolCode(long schoolCode) {
		this.schoolCode = schoolCode;
	}
	public long getTotalCreadit() {
		return totalCreadit;
	}
	public void setTotalCreadit(long totalCreadit) {
		this.totalCreadit = totalCreadit;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	
	public double getCGPA() {
		return CGPA;
	}
	public void setCGPA(double cGPA) {
		CGPA = cGPA;
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
