package in.co.school.mgt.bean;

import java.util.Date;

public class AttendanceBean extends BaseBean {
	
	
	private long studentId;
	private String name;
	private long classId;
	private String className;
	
	private String status;
	private Date date;
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

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
