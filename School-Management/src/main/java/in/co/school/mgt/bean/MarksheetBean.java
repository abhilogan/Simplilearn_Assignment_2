package in.co.school.mgt.bean;

public class MarksheetBean extends BaseBean {

	
	private long rollNo;
	private long classId;
	private String className;
	private long subjectId;
	private String subjectName;
	private long studentId;
	private String studentName;
	private long schoolCode;
	private long mark;
	private String grade;
	private String result;
	
	
	
	
	
	
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public long getRollNo() {
		return rollNo;
	}

	public void setRollNo(long rollNo) {
		this.rollNo = rollNo;
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

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
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

	public long getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(long schoolCode) {
		this.schoolCode = schoolCode;
	}

	public long getMark() {
		return mark;
	}

	public void setMark(long mark) {
		this.mark = mark;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
